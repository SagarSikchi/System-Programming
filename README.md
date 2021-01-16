# System-Programming
System Programming is the core subject of the computer science. Systems programming involves the development of the individual pieces of software that allow the entire system to function as a single unit. Systems programming involves many layers such as the operating system (OS), firmware, and the development environment. To understand the system programming, first one need to understand the following terminologies:

 - **System:** System is the collection of the various components. Example: **_College_** is the system.
 - **Programming:** It is the art of designing and implementing the programs.
 - **System Program:** These are programs which are required for the effective execution of general user programs on computer system.
 - **System Programming:** It is an art of designing and implementing system programs.
 
 ## Components of System Programming

Following are the components of the system programming:

### 1. Interpreter

Interpreter is the language translator which execute source program line by line with out translating them into machine language.
Types of Interpreter are -

- **Pure Interpreter:** In this case no preprocessing is required on source program before an interpretation starts.
- **Non-pure Interpreter:** Some preprocessing is required on source program before an interpretation starts.

Checkout the [**_Math Interpreter_**]() which I implemented.

### [2. Assembler]()

These are the system programs which will automatically translate the assembly language program in to the machine language program.

**Cross Assembler:** These are the system programs which will automatically translate the assembly level language program compatible with machine **A** into a machine level language program compatible with the machine **B**.

In this course, I have implemented the **Pass I** and **Pass II** of the assembler. For this, first we need to know the [**_Symbol Table_**](). Symbol table is the table which stores the literals and symbols which are defined in the system programs. This table is machine independent and used for solving the **Forward Reference** problem in the assembler. **Forward Reference** is the problem which arises when a variable is used before its declaration in the program. For example,
        
        LOAD A, FOUR
        LOAD B, 10
        FOUR DS '4'
        
        where, A, B are the registers and FOUR is the variable which is used in the first line but declared after in the last line.
To solve this problem, Pass I and Pass II are implemented in the assembler where, Symbol table is generated in the Pass I and then correct addresses of the symbols/literals are added in the symbol table.

### 3. Compiler

These are the system programs which will automatically translate the High level language program in to the machine language program.

**Cross Compiler:** These are the system programs which will automatically translate the high level language program compatible with machine **A** into a machine level language program compatible with the machine **B**.

Compiler has the following six phases:

- [**Lexical Analyser:**]() It is the phase where the lexims are created and tokens(collection of the similar lexims) are generated.
- **Syntax Analyser:** It is the tokenization process. The parse tree is generated using different types of the parsers and syntax of the expression is verified.
- **Semantic Analyser:** Meaning of the expression is verified using context-free grammar rules and productions.
- **Intermediate Code Generator:** Simplified code is generated in the assembly language.
- **Code Optimizer:** Redundancy of the intermediate code is removed.
- **Final Code Generator:** Final optimized machine level code is generated.


### [4. Macros and Nested Macros]()

A **Macro** instruction is the notational convenience for the programmer. For every occurrence of macro the whole macro body or macro block of statements gets expanded in the main source code. Thus Macro instructions make writing code more convenient. Salient features of Macro Processor: 
- Macro represents a group of commonly used statements in the source programming language.
- Macro Processor replaces each macro instruction with the corresponding group of source language statements. This is known as the expansion of macros.
- Using Macro instructions programmer can leave the mechanical details to be handled by the macro processor.
- Macro Processor designs are not directly related to the computer architecture on which it runs.
- Macro Processor involves definition, invocation, and expansion.

**Nested Macros** are the macros within the macros. Macro is a single line abbreviation for a group of instruction.

      MACRO	--------> Start of definition
      INCR  --------> Macro name
       A 1,DATA
       A 2,DATA --> Sequence of instructions to be abbreviated.
       A 3,DATA
      MEND	--------> End of definition


Formal systems
Debugger
Linkers 
Operating system
