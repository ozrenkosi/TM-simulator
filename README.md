TM simulator
==============

Solution for the fifth lab at "Introduction to Theoretical Computer Science" course - Turing Machine simulator.

Example
------

Input file consists rows in this specific order:

1. line - states separated with `,`
2. line - input symbols separated with `,`
3. line - tape symbols separated with `,`
4. line - empty cell symbol
5. line - tape
6. line - acceptable states separated with `,`
7. line - starting state
8. line - starting position
9. line and following lines - transition function in format 
   `currentState,currentTapeSymbol->newState,newTapeSymbol,headMovement`

Input

    q0,q1,q2,q3,q4
    0,1
    0,1,X,Y,B
    B
    0011BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB
    q4
    q0
    0
    q0,0->q1,X,R
    q1,0->q1,0,R
    q2,0->q2,0,L
    q1,1->q2,Y,L
    q2,X->q0,X,R
    q0,Y->q3,Y,R
    q1,Y->q1,Y,R
    q2,Y->q2,Y,L
    q3,Y->q3,Y,R
    q3,B->q4,B,R

will give output

    q4|5|XXYYBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB|1

Output format is `state|position|tape|isInAcceptableStates`.
