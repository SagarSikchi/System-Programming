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


### 5. Formal Systems

A **formal system** is an un interpreted calculus. It consists of - 
- Alphabets
- A set of words called Axioms.
- Finite set of relations called rules of inference or production rules. Example: **_Boolean algebra_**.

### 6. Debugger

A debugger or debugging tool is a computer program used to test and debug other programs (the "target" program). The main use of a debugger is to run the target program under controlled conditions that permit the programmer to track its operations in progress and monitor changes in computer resources (most often memory areas used by the target program or the computer's operating system) that may indicate malfunctioning code. Typical debugging facilities include the ability to run or halt the target program at specific points, display the contents of memory, CPU registers or storage devices (such as disk drives), and modify memory or register contents in order to enter selected test data that might be a cause of faulty program execution.

Some widely used debuggers are:

- Arm DTT, formerly known as Allinea DDT
- Eclipse debugger API used in a range of IDEs: Eclipse IDE (Java) Nodeclipse (JavaScript)
- Firefox JavaScript debugger
- GDB - the GNU debugger
- LLDB
- Microsoft Visual Studio Debugger
- Radare2
- TotalView
- Valgrind
- WDW, the OpenWatcom debugger
- WinDbg

### [7. Loader and Linker]()

A **Loader** is the system program that place the object program into main memory and prepares it for execution. The loader is special program that takes input of object code from linker, loads it to main memory, and prepares this code for execution by computer. Loader allocates memory space to program. Even it settles down symbolic reference between objects. It in charge of loading programs and libraries in operating system. The embedded computer systems don’t have loaders. In them, code is executed through ROM.
Basic functions of loader:
- **Allocation:**
- **Linking:** 
- **Relocation:**
- **Loading:**

Types of the loaders are-

- Compile-and-go Loader
- Relocating Loader
- Direct Linking Loader
- Absolute Loader
- General Loader
- Dynamic Loader

**Linking** is the process of merging many object modules to form a single object program is called as linking. **Linker** is the software program which binds many object modules to make a single object program. A linker is special program that combines the object files, generated by compiler/assembler, and other pieces of codes to originate an executable file have. exe extension. In the object file, linker searches and append all libraries needed for execution of file. It regulates memory space that code from each module will hold. It also merges two or more separate object programs and establishes link among them.
Types of the linkers are-

- **Static Linker/Linkage Editor:** Linking during the compilation.
- **Dynamic Linker:** Runtime linking.

[Difference between Linker and Loader](https://www.geeksforgeeks.org/difference-between-linker-and-loader/#:~:text=The%20main%20function%20of%20Linker,executable%20files%20to%20main%20memory.&text=The%20linker%20takes%20input%20of,executable%20files%20generated%20by%20linker)

### 8. Operating system

It is the collection of system programs which acts as an interface between user and the computer and computer hardware. The purpose of an operating system is to provide an environment in which a user can execute programs in a convenient manner. Functions of Operating System are:

- File handling and management
- Storage management(Memory management)
- Device scheduling and management
- CPU scheduling
- Information management
- Process control (management)
- Error handling
- Protecting itself from user & protecting user from other users


## Need Of System Programming

The basic need of system programming is to achieve the following goals :-

- To achieve efficient performance of the system
- To make effective execution of general user program 
- To make effective utilization of human resources
- To make available new and better facilities


## How to run the programs?

To run the java programs given above, the prerequisites are -

- Need Java language support in your laptop or computer(JDK and JRE installed).
- Editor like Intellij or Visual Studio Code

After this, the input and output file paths are need to be correctly specified. Download the zip folder and extract it on Desktop. Then open the each folder to run the programs. Or else, specify the path where your want to run the programs within the java source code files.

