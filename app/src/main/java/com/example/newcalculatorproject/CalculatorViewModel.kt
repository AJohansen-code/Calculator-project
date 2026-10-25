package com.example.newcalculatorproject

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class CalculatorViewModel: ViewModel() {

    var state by mutableStateOf(CalculatorState())
        private set

    fun onAction(action: CalculatorActions) {
        when (action) {
            is CalculatorActions.Number -> enterNumber(action.number)
            is CalculatorActions.Parentheses -> enterParentheses()
            is CalculatorActions.Percentage -> enterPercentage()
            is CalculatorActions.Decimal -> enterDecimal()
            is CalculatorActions.Clear -> state = CalculatorState(isDarkTheme = state.isDarkTheme)
            is CalculatorActions.Operation -> enterOperation(action.operation)
            is CalculatorActions.Calculate -> performCalculation()
            is CalculatorActions.Delete -> performDeletion()
            is CalculatorActions.ToggleTheme -> state = state.copy(isDarkTheme = !state.isDarkTheme)
        }
    }

    private fun enterNumber(number: Int) {
        state = state.copy(expression = state.expression + number)
    }

    private fun enterOperation(operation: CalculatorOperation) {
        val expression = state.expression
        val lastChar = expression.lastOrNull()
        
        if (lastChar == null) {
            if (operation is CalculatorOperation.Subtract) {
                state = state.copy(expression = "-")
            }
            return
        }

        if (lastChar.isDigit() || lastChar == ')') {
            state = state.copy(expression = expression + operation.symbol)
        } 
        else if (lastChar.toString() in "+-*/") {
            state = state.copy(expression = expression.dropLast(1) + operation.symbol)
        }
        else if (lastChar == '(' && operation is CalculatorOperation.Subtract) {
            state = state.copy(expression = expression + "-")
        }
    }

    private fun enterPercentage() {
        performCalculation()
        val result = state.expression.toDoubleOrNull()
        if (result != null) {
            state = state.copy(expression = (result / 100.0).toString().removeSuffix(".0"))
        }
    }

    private fun enterParentheses() {
        val expression = state.expression
        val openCount = expression.count { it == '(' }
        val closeCount = expression.count { it == ')' }
        val lastChar = expression.lastOrNull()

        if (openCount > closeCount && lastChar != null && (lastChar.isDigit() || lastChar == ')')) {
            state = state.copy(expression = "$expression)")
        } else {
            if (lastChar == null || lastChar.toString() in "+-*/(") {
                state = state.copy(expression = "$expression(")
            } else if (lastChar.isDigit() || lastChar == ')') {
                state = state.copy(expression = "$expression*(")
            }
        }
    }

    private fun performDeletion() {
        if (state.expression.isNotEmpty()) {
            state = state.copy(expression = state.expression.dropLast(1))
        }
    }

    private fun performCalculation() {
        val result = evaluate(state.expression)
        if (result != null) {
            state = state.copy(expression = result.removeSuffix(".0"))
        }
    }

    private fun enterDecimal() {
        val lastPart = state.expression.split('+', '-', '*', '/', '(', ')').last()
        if (!lastPart.contains('.') && (lastPart.isEmpty() || lastPart.last().isDigit())) {
            state = state.copy(expression = state.expression + ".")
        }
    }

    private fun evaluate(expr: String): String? {
        return try {
            val result = object : Any() {
                var pos = -1
                var ch = 0

                fun nextChar() {
                    ch = if (++pos < expr.length) expr[pos].toInt() else -1
                }

                fun eat(charToEat: Int): Boolean {
                    while (ch == ' '.toInt()) nextChar()
                    if (ch == charToEat) {
                        nextChar()
                        return true
                    }
                    return false
                }

                fun parse(): Double {
                    nextChar()
                    val x = parseExpression()
                    if (pos < expr.length) return Double.NaN
                    return x
                }

                fun parseExpression(): Double {
                    var x = parseTerm()
                    while (true) {
                        if (eat('+'.toInt())) x += parseTerm()
                        else if (eat('-'.toInt())) x -= parseTerm()
                        else return x
                    }
                }

                fun parseTerm(): Double {
                    var x = parseFactor()
                    while (true) {
                        if (eat('*'.toInt())) x *= parseFactor()
                        else if (eat('x'.toInt())) x *= parseFactor()
                        else if (eat('/'.toInt())) x /= parseFactor()
                        else return x
                    }
                }

                fun parseFactor(): Double {
                    if (eat('+'.toInt())) return parseFactor()
                    if (eat('-'.toInt())) return -parseFactor()
                    var x: Double
                    val startPos = pos
                    if (eat('('.toInt())) {
                        x = parseExpression()
                        eat(')'.toInt())
                    } else if (ch >= '0'.toInt() && ch <= '9'.toInt() || ch == '.'.toInt()) {
                        while (ch >= '0'.toInt() && ch <= '9'.toInt() || ch == '.'.toInt()) nextChar()
                        x = expr.substring(startPos, pos).toDouble()
                    } else {
                        return Double.NaN
                    }
                    return x
                }
            }.parse()
            if (result.isNaN()) null else result.toString()
        } catch (e: Exception) {
            null
        }
    }

    companion object {
        private const val MAX_NUM_LENGTH = 8
    }
}
