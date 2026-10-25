package com.example.newcalculatorproject

sealed class CalculatorActions {
    data class Number(val number: Int) : CalculatorActions()
    object Clear : CalculatorActions()
    object Delete : CalculatorActions()
    object Decimal : CalculatorActions()
    object Calculate : CalculatorActions()
    object Parentheses : CalculatorActions()
    object Percentage : CalculatorActions()
    object ToggleTheme : CalculatorActions()
    data class Operation(val operation: CalculatorOperation) : CalculatorActions()
}
