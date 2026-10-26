# Calculator-Project
Rebuild & Improve the A Jetpack Compose Calculator App Due Date Oct 27

### Technical Toolkit
- **Languages:** Kotlin
- **Tools:** GitHub, Android Studio, LogicWorks 5.6, SDL2

### Brief summary 
For this assignment project, I created a Calculator in Kotlin language through Android studio.
For the mobile app creation I created a clean interface where it can handle math calculations. 
Providing the usual buttons expected on calculators: addition, subtraction, multiplication, division, equal, and erase. 
When it comes to the breakdown of the project, I used the Android app design principles: 

- **UI Layer** Composing components, example (Calculator, calculatorButton), where it will react to the users inputs.
 Letting users to type in there calculations and seeing the results. 
- **State Management** This handles the apps state using the immutable data class which is triggered by the users actions.
- **Logic** This is where it manages the all the state changes within the app (CalculatorViewModel). Where it dose all the heavy math calculations away from the UI.

### Setup and Run Steps
When creating the app, everything was original done inside Android Studio and then later transferred to GitHub repo. The setup when creating the calculator has a few parts:
- **Layout** I began structuring out how the layout for the app will look like by separating the logics, this will help the app not get tangled up with UI designs and the logic of the math operations.
  Each layout was broken up into 8 different classes/ files: UI, Calculator, CalculatorActions, CalculatorButtons, CalculatorOperations, CalculatorState, CalculatorViewModel, and MainActivity.
- **Functions**
  Here I will quickly explain what each Classes/ Files roles and what they do in the project.
  - UI: this is where the main design of the app is stored (style, color, and type)
  - Calculator: This is where each button of the calculator gets specific designed and spaces for the frontend of the app, the display screen and keypad grid. 
  - CalculatorActions: This is where the sealed classes/ interface represented in every possible user input event. 
  - CalculatorButtons: This is where the buttons are reused in a single button building block for the keypad.
  - CalculatorOperations: This is where we see how the operations of the four basic operations (+, -, *, /), %, and () are represented in there sealed classes. 
  - CalculatorState: This is the immutable data holder of the entire UI of the Calculator states at any point of time. 
  - CalculatorViewModel: This is where that state holder and logic of the app engines survives when/ if the configurations changes. 
  - MainActivity: This is where the apps entry point is and where is it hosts the app 

  When the app is fully established, then we can start to run the system. Here is what the Run setup looks like in these parts:
  - **Starting up** We start by either running it in Android studio or GitHub, it was open up the app and show the UI of the calculator. It will have a 4 x 5 button grid of the operators: 0-9, Four Basic Arithmetic Operations (+,-,*,/), Clear (AC), percentage, parentheses, and equal buttons.
  - **Final result** Once the app opens, users can type in the app and start putting in math problems and find the solution. An example would be putting in the equation: (200*86)/4, once it has been inputted the app will show the correct solution being 4,300.
  Showing from beginning to end how the app handles the inputs and gives the final results. 

### Architecture and Trade-off
For the layout of the project I'll be breaking down each part of the architecture, MVI and Separation, and trade-off, primitive/String state and fixed aspect grid/ adaptive layout.
Explaining why I choose these specific setups for my app.

- **MVI**
  This will keep the app in a state where it simple cause it uses a single source of truth, going from the 'CalculatorState' to 'CalculatorViewModel'.
  Having the users action (CalculatorActions) input send events to the view model through the inAction(), which then updates the state and push changes to the UI.
    
- **Separation**
 By separating codes classes into a clean layout, it helps the math portion of the app not get tangled up with the evaluation logic 
 
- **Primitive/String state**
Trade-off: Keeping the number stored in a simple string, which will help the keep it calculated when needed, example 'number1 and number2'.
Impact: This makes handling the inputs easier manage while also making the basic two number math straightforward without the tricky chain complex math messing up the operations, example the app able to operator priorities (5+9*7)= 68
without having to rework in the parser.
  
- **Fixed aspect grid/ adaptive layout**
Trade-off: With the way the UI is designed, I had the buttons use a fixed sizing. In doing so would only have the calculator be used properly in a vertical position (phone screen mode). 
Impact: Other than it being fixed to a specific position, the app dose work right out of the box on phones. However, it dose hinder the design if tried to use it in horizonal position. In my opinion, a calculator works best in horizontal because who turns a regular/ scientific calculator sideways/ vertical. 

### Calculator enhancements
The enhancements I used in this project were a percentage and parentheses button. 
I wanted to implement the same button setup just like what my phone calculator already has. It gives me a sense of familiarity. 
Though my project is not as complex as what a major tech company can create, it still gets the job done. 
With these enhancements I gives the calculator a chance to do more complicated problems. 

- **Parentheses** The parentheses forces the standard order of operations to do the calculations inside the parentheses first before any other value, example: (85*4)+6=426.
- **percentage** While the percentage automatically converts the number into decimal equivalent, giving users the ability to calculate proportions quickly, example: 200*50%=100.
- **Dark/Light Mode** This gives the app users a chance to change how they would like the app to look like in either light or dark mode, even added a simple emoji for the sun and moon, so that users know which to pick. In my opinion, dark mode is better on the eyes.
- **Color Change Buttons** This allows a bit of a creative design to the app. It maybe small but it is still impressive visual part of the project. This is where you can see each button when press will turn a lighter color from the main. 

### Refection 
 This was a fun project as I got to see the building blocks of how a "simple" app that is the calculator is created and what the finished product looks like. It was fascinating to see my calculator works properly almost the same as the one on my personal phone. 
 But throughout this project there were a few things I wished that I had added to make it feel a little more professional. 
 One would be adding a history function in the app, allowing user to be able to look back what they last put in without redoing the work. I also would help users identify any issues in there last equation if there was any mistypes. 
 Second would be building a responsive screen design. This would allow users to not be stuck with a fixed button grid. This would allow the app to be able to open up in any format without issues.  

### A way to reach me
- School email **aracosta@ualr.edu**

---
*"If you fall flat on your face, at least you're moving forward. All you have to do is get back up and try again." – Rita Moreno.*
