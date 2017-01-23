##Tak, A Beautiful Game

##Launch Instructions
In order to run this Tak game, run Woo.java in the terminal.

##Game Description
Tak is a board game taken out of the KingKiller Chronicles and made into reality. Our project will be a terminal implementation of Tak. It is a two player game that can be played on various different board sizes ranging from 3x3 to 8x8, although the most common is 5x5. Using the same rules, we will implement our project using player entered commands. The board will be reprinted after each update. Similar to a chess or checkers game implementation, players will enter different commands and use a grid to identify spaces on a board. The first person to create a “road” with their “stones” between two opposite sides of the board wins. Roads do not have to be in a straight line. Players will attack, capture and defend their own pieces.
Official rules can be found at: <http://cheapass.com/free-games/tak/>

##Meet the Pieces
Stone: a stone is your standard piece in Tak. it is counted as a part of roads, and can be moved anywhere, except on capstones and walls.
Wall: a wall is a standing stone. It is used to block roads from being formed. It can be moved anywhere, except on other walls and capstones. In addition, it can be flattened by capstones, essentially turning it back into a regular stone.
Capstone: a capstone is a special piece that can be moved anywhere, except on other capstones. It has the power to flatten walls back into stones.

##Placing a Piece
Inputs in Tak work as follows: the first integer that you input when prompted for a location indicates which row you'll be placing a piece in. The second number indicates the column. We apologize if there is any confusion with this system. In addition, in our version of Tak, the white piece always moves first.
