

    public class ConwayGameOfLife {

        // Concepts demonstrated: state abstraction, discrete dynamical systems,
        // modular arithmetic for boundary conditions, and procedural decomposition.

        // This program implements a subset of Conway’s Game of Life. The world is
        // represented as a two-dimensional grid of cells with binary state (alive
        // or dead), and a fixed set of deterministic rules is applied iteratively
        // to evolve the system forward in time.

        // The implementation emphasizes clarity of state representation and
        // explicit rule application rather than abstraction or performance
        // optimization. Toroidal boundary conditions are enforced via modular
        // arithmetic. Initial configurations are constructed by placing multiple
        // copies of known patterns (e.g. gliders, pulsars, LWSS) at random locations.

        // StdDraw.java is required for visualization.

        //The next three methods are pattern initialization helpers

        //Takes inputs: int[][] state0 (called "a" here), int r (representing row), and int c (representing column), and initializes Glider
        // pattern around entry: state0[r][c], following rule that 1 represents a live cell and 0 represents a dead cell.
        // Note that input array comes in with entries initialized to 0.
        public static void Glider(int[][] a, int r, int c ){
            int r_Minus_1_index = (a.length + r-1) % a.length;
            int r_Minus_2_index = (a.length + r-2) % a.length;

            int c_Plus_1_index = (c+1) % a.length;
            int c_Minus_1_index = (a.length + c-1) % a.length;

            a[r][c] = 1;
            a[r][c_Minus_1_index] = 1;
            a[r][c_Plus_1_index] = 1;
            a[r_Minus_1_index][c_Plus_1_index] = 1;
            a[r_Minus_2_index][c] = 1;
        }

        //Same as Glider method above but for Pulsar "shape"
        public static void Pulsar(int[][] a, int r, int c){

            a[r][c] = 1;
            a[r][(a.length + c-1) % a.length] = 1;
            a[r][ (c+1) % a.length] = 1;
            a[ (a.length + r-1) % a.length][ (a.length + c - 3) % a.length] = 1;
            a[ (a.length + r-2) % a.length][ (a.length + c-3) % a.length] = 1;
            a[ (a.length + r-3) % a.length][ (a.length + c-3) % a.length] = 1;
            a[ (a.length + r-5) % a.length][ (a.length + c-1) % a.length] = 1;
            a[ (a.length + r-5) % a.length][c] = 1;
            a[ (a.length + r-5) % a.length][ (c+1) % a.length] = 1;
            a[ (a.length + r-3) % a.length][(c+2) % a.length] = 1;
            a[ (a.length + r-2) % a.length][(c+2) % a.length] = 1;
            a[ (a.length + r-1) % a.length][(c+2) % a.length] = 1;

            a[r][ (c+5) % a.length] = 1;
            a[r][ (c+6) % a.length] = 1;
            a[r][ (c+7) % a.length] = 1;
            a[(a.length + r-1) % a.length][ (c+4) % a.length] = 1;
            a[(a.length + r-2) % a.length][ (c+4) % a.length] = 1;
            a[(a.length + r-3) % a.length][ (c+4) % a.length] = 1;
            a[(a.length + r-5) % a.length][ (c+5) % a.length] = 1;
            a[(a.length + r-5) % a.length][ (c+6) % a.length] = 1;
            a[(a.length + r-5) % a.length][ (c+7) % a.length] = 1;
            a[(a.length + r-3) % a.length][ (c+9) % a.length] = 1;
            a[(a.length + r-2) % a.length][ (c+9) % a.length] = 1;
            a[(a.length + r-1) % a.length][ (c+9) % a.length] = 1;

            a[ (r+2) % a.length][ (a.length + c-1) % a.length] = 1;
            a[ (r+2) % a.length][c] = 1;
            a[ (r+2) % a.length][(c+1) % a.length] = 1;
            a[ (r+3) % a.length][(a.length + c-3) % a.length] = 1;
            a[ (r+4) % a.length][(a.length + c-3) % a.length] = 1;
            a[ (r+5) % a.length][(a.length + c-3) % a.length] = 1;
            a[ (r+7) % a.length][(a.length + c-1) % a.length] = 1;
            a[ (r+7) % a.length][c] = 1;
            a[ (r+7) % a.length][ (c+1) % a.length] = 1;
            a[ (r+3) % a.length][ (c+2) % a.length] = 1;
            a[ (r+4) % a.length][ (c+2) % a.length] = 1;
            a[ (r+5) % a.length][ (c+2) % a.length] = 1;

            a[ (r+2) % a.length][ (c+5) % a.length] = 1;
            a[ (r+2) % a.length][ (c+6) % a.length] = 1;
            a[ (r+2) % a.length][ (c+7) % a.length] = 1;
            a[ (r+3) % a.length][ (c+4) % a.length] = 1;
            a[ (r+4) % a.length][ (c+4) % a.length] = 1;
            a[ (r+5) % a.length][ (c+4) % a.length] = 1;
            a[ (r+7) % a.length][ (c+5) % a.length] = 1;
            a[ (r+7) % a.length][ (c+6) % a.length] = 1;
            a[ (r+7) % a.length][ (c+7) % a.length] = 1;
            a[ (r+3) % a.length][ (c+9) % a.length] = 1;
            a[ (r+4) % a.length][ (c+9) % a.length] = 1;
            a[ (r+5) % a.length][ (c+9) % a.length] = 1;
        }

        //Same as Glider pattern above but for LWSS "shape"
        public static void LWSS(int[][] a, int r, int c){
            int r_Minus_1_index = (a.length + r-1) % a.length;
            int r_Minus_2_index = (a.length + r-2) % a.length;
            int r_Plus_1_index = (r+1) % a.length;

            int c_Plus_1_index = (c+1) % a.length;
            int c_Plus_2_index = (c+2) % a.length;
            int c_Plus_3_index = (c+3) % a.length;
            int c_Plus_4_index = (c+4) % a.length;

            a[r][c] = 1;
            a[r_Minus_2_index][c] = 1;
            a[r_Minus_2_index][c_Plus_3_index] = 1;
            a[r_Minus_1_index][c_Plus_4_index] = 1;
            a[r][c_Plus_4_index] = 1;
            a[r_Plus_1_index][c_Plus_4_index] = 1;
            a[r_Plus_1_index][c_Plus_3_index] = 1;
            a[r_Plus_1_index][c_Plus_2_index] = 1;
            a[r_Plus_1_index][c_Plus_1_index] = 1;
        }

        public static void setupStdDraw(int numCells1){
            StdDraw.enableDoubleBuffering();
            StdDraw.setCanvasSize(numCells1 * 4, numCells1 * 4);
            StdDraw.setXscale(0, (numCells1 +1) * 4);
            StdDraw.setYscale(0, (numCells1 + 1) * 4);
            StdDraw.setPenColor(StdDraw.PRINCETON_ORANGE);
        }

        //Using data representation of 0 or 1 state to actually draw a filled square for the 1 states, itereating over each
        //grid square (i.e entry in the int[][])
        public static void lifeDraw(int[][] currentstate){
            double halfLength = 2;
            for(int r = 0; r < currentstate.length; r++){
                for(int c = 0; c < currentstate[r].length; c++){
                    if (currentstate[r][c] == 1){
                        StdDraw.filledSquare(c * 4 + halfLength, (currentstate.length - r) * 4 - 2, halfLength);
                    }
                }
            }
        }

        //Applies rules of game of life to currentState
        public static int[][] evolveState(int[][] currentState){
            int[][] nextState = new int[currentState.length][currentState.length];
            for(int r = 0; r < currentState.length; r++){
                for(int c = 0; c < currentState.length; c++){
                    //Representing surrounding cell states starting left of current cell and moving clockwise. Note the use
                    //of modular arithmetic to wrap for torus structure of grid.
                    int leftCell = currentState[r][(currentState.length + c-1) % currentState.length];
                    int leftUpCell = currentState[(currentState.length + r-1) % currentState.length][(currentState.length + c-1) % currentState.length];
                    int upCell = currentState[(currentState.length + r-1) % currentState.length][c];
                    int rightUpCell = currentState[(currentState.length + r-1) % currentState.length][(c+1) % currentState.length];
                    int rightCell = currentState[r][(c+1) % currentState.length];
                    int rightDownCell = currentState[(r+1) % currentState.length][(c+1) % currentState.length];
                    int downCell = currentState[(r+1) % currentState.length][c];
                    int downLeftCell = currentState[(r+1) % currentState.length][(currentState.length + c-1) % currentState.length];
                    int surroundingCellStateSum = leftCell + leftUpCell + upCell + rightUpCell + rightCell + rightDownCell + downCell + downLeftCell;

                    //The following rules are applied to manipulate data if a given cell in the current state is "alive"
                    if(currentState[r][c] == 1){
                        //Rule 1: "Any live cell with fewer than two live neighbours dies, as if by underpopulation."
                        if(surroundingCellStateSum < 2){
                            nextState[r][c] = 0;
                        }
                        //Rule 2: "Any live cell with two or three live neighbours lives on to the next generation."
                        else if((surroundingCellStateSum == 2) || (surroundingCellStateSum == 3)){
                            nextState[r][c] = 1;
                        }
                        //Rule 3: "Any live cell with more than three live neighbours dies, as if by overpopulation."
                        else{
                            nextState[r][c] = 0;
                        }
                    }
                    //The following rule is applied if a given cell is "dead"
                    else{
                        //Rule 4: "Any dead cell with exactly three live neighbours becomes a live cell, as if by
                        // reproduction."
                        if(surroundingCellStateSum == 3){
                            nextState[r][c] = 1;
                        }
                    }
                }

            }

            //Setting current state array to the evolved(by one step) next state array. Note that this drops the pointer
            //to the former 'current state array' held by the variable "currentState", which causes the computer to delete
            // that array, by assigning/replacing that pointer with the pointer value of the evolved 'next state array'
            return nextState;
        }

        //public static void initializer(int[][] initialState, int numCopies){

        //}


        public static void main(String[] args){

            if( args.length != 2 ){
                System.err.println("This program takes two command line arguments. The first is the number of cells along " +
                        "each edge of the world and the second is the number of copies of each initial pattern");
                System.exit(1);
            }

            int numCells = Integer.parseInt(args[0]);
            int numCopies = Integer.parseInt(args[1]);

            if(numCells < 1 || numCopies < 1){
                System.err.println("This program takes two command line arguments. The first is the number of cells along " +
                        "each edge of the world and the second is the number of copies of each initial pattern");
                System.exit(1);
            }

            //Setup double buffering, canvas size/scale, etc.
            setupStdDraw(numCells);

            //Initialize state array of arrays representing grid of cells with 1's being 'alive' and 0's being 'dead'. Note
            //all entries initialized to 0 (i.e dead)
            int[][] currentState = new int[numCells][numCells];

            int tempR = 0;
            int tempC = 0;

            for(int i = 0; i < numCopies; i++){
                tempR = (int)(Math.random() * numCells);
                tempC = (int)(Math.random() * numCells);
                Glider(currentState, tempR, tempC);
                tempR = (int)(Math.random() * numCells);
                tempC = (int)(Math.random() * numCells);
                Pulsar(currentState, tempR, tempC);
                tempR = (int)(Math.random() * numCells);
                tempC = (int)(Math.random() * numCells);
                LWSS(currentState, tempR, tempC);
            }

            //Drawing initial config
            lifeDraw(currentState);
            StdDraw.show();

            int dt = 20;

            //Main simulation loop: evolve state, render, pause, clear canvas, repeat
            while(true){
                currentState = evolveState(currentState);
                lifeDraw(currentState);
                StdDraw.show();
                StdDraw.pause(dt);
                StdDraw.clear();
            }


        }


    }

