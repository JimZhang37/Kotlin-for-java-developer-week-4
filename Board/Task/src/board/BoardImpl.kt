package board

import board.Direction.*

fun createSquareBoard(width: Int): SquareBoard {

    return object : SquareBoard {
        val cells = mutableListOf<Cell>()

        init{
            var i = width

            while (i > 0) {
                var j = width
                while (j > 0) {

                    cells += Cell(i, j)
                    j--
                }


                i--

            }


        }

        override val width: Int
            get() = width //To change initializer of created properties use File | Settings | File Templates.

        override fun getCellOrNull(i: Int, j: Int): Cell? {
            if (i > this.width || j > this.width) return null
            return cells.find{it.i ==i && it.j ==j}

            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun getCell(i: Int, j: Int): Cell {
            if (i > this.width || j > this.width) throw IllegalArgumentException()
            val cell: Cell? = cells.find{it.i ==i && it.j ==j}
            if (cell == null ) throw IllegalArgumentException()
            return cell!!
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun getAllCells(): Collection<Cell> {

            return cells
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun getRow(i: Int, jRange: IntProgression): List<Cell> {



            val first = jRange.first
            val second = jRange.last
            var max = minOf(maxOf(first, second), width)
            val min = minOf(first, second, width)

            if(first < second) return cells.filter { it.i == i && it.j >=min && it.j <= max}.sortedWith(compareBy<Cell> { it.i }.thenBy { it.j })

            return cells.filter { it.i == i && it.j >=min && it.j <= max}.sortedWith(compareBy<Cell> { it.i }.thenBy { it.j }).reversed()



            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun getColumn(iRange: IntProgression, j: Int): List<Cell> {


            val first = iRange.first
            val second = iRange.last
            var max = minOf(maxOf(first, second), width)
            val min = minOf(first, second, width)



            if (first < second)return cells.filter { it.j == j && it.i >=min && it.i <= max}.sortedWith(compareBy<Cell> { it.i }.thenBy { it.j })
            return cells.filter { it.j == j && it.i >=min && it.i <= max}.sortedWith(compareBy<Cell> { it.i }.thenBy { it.j }).reversed()


            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun Cell.getNeighbour(direction: Direction): Cell? {
            var cell:Cell? = null

            when (direction) {
                Direction.UP -> if ((this.i-1)>=1) cell = cells.find{it.i == this.i-1 && it.j == this.j}
                Direction.DOWN ->if ((this.i+1)<=width) cell = cells.find{it.i == this.i+1 && it.j == this.j}
                Direction.LEFT -> if(this.j-1 >= 1) cell = cells.find{it.i == this.i && it.j == this.j-1}
                Direction.RIGHT -> if(this.j+1 <= width) cell = cells.find{it.i == this.i && it.j == this.j+1}

            }
            return cell
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }


    }
}

fun <T> createGameBoard(width: Int): GameBoard<T> = object : GameBoard<T> {


    val cells = mutableListOf<Cell>()
    val map = mutableMapOf<Cell, T>()
    init{
        var i = width

        while (i > 0) {
            var j = width
            while (j > 0) {

                cells += Cell(i, j)
                j--
            }


            i--

        }


    }

    override val width: Int
        get() = width //To change initializer of created properties use File | Settings | File Templates.

    override fun getCellOrNull(i: Int, j: Int): Cell? {
        if (i > this.width || j > this.width) return null
        return cells.find{it.i ==i && it.j ==j}

        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getCell(i: Int, j: Int): Cell {
        if (i > this.width || j > this.width) throw IllegalArgumentException()
        val cell: Cell? = cells.find{it.i ==i && it.j ==j}
        if (cell == null ) throw IllegalArgumentException()
        return cell!!
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAllCells(): Collection<Cell> {

        return cells
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getRow(i: Int, jRange: IntProgression): List<Cell> {



        val first = jRange.first
        val second = jRange.last
        var max = minOf(maxOf(first, second), width)
        val min = minOf(first, second, width)

        if(first < second) return cells.filter { it.i == i && it.j >=min && it.j <= max}.sortedWith(compareBy<Cell> { it.i }.thenBy { it.j })

        return cells.filter { it.i == i && it.j >=min && it.j <= max}.sortedWith(compareBy<Cell> { it.i }.thenBy { it.j }).reversed()



        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getColumn(iRange: IntProgression, j: Int): List<Cell> {


        val first = iRange.first
        val second = iRange.last
        var max = minOf(maxOf(first, second), width)
        val min = minOf(first, second, width)



        if (first < second)return cells.filter { it.j == j && it.i >=min && it.i <= max}.sortedWith(compareBy<Cell> { it.i }.thenBy { it.j })
        return cells.filter { it.j == j && it.i >=min && it.i <= max}.sortedWith(compareBy<Cell> { it.i }.thenBy { it.j }).reversed()


        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun Cell.getNeighbour(direction: Direction): Cell? {
        var cell:Cell? = null

        when (direction) {
            Direction.UP -> if ((this.i-1)>=1) cell = cells.find{it.i == this.i-1 && it.j == this.j}
            Direction.DOWN ->if ((this.i+1)<=width) cell = cells.find{it.i == this.i+1 && it.j == this.j}
            Direction.LEFT -> if(this.j-1 >= 1) cell = cells.find{it.i == this.i && it.j == this.j-1}
            Direction.RIGHT -> if(this.j+1 <= width) cell = cells.find{it.i == this.i && it.j == this.j+1}

        }
        return cell
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }



    override fun get(cell: Cell): T? {
        return map[cell]
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun set(cell: Cell, value: T?) {
        if(value != null) map[cell]=value
    }

    override fun filter(predicate: (T?) -> Boolean): Collection<Cell> {
        return map.filter { mapi-> predicate(mapi.value) }.map{mapj -> mapj.key}
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun find(predicate: (T?) -> Boolean): Cell? {
        return map.filter { mapi-> predicate(mapi.value) }.map{mapj -> mapj.key}[0]
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun any(predicate: (T?) -> Boolean): Boolean {
        return cells.any{cell-> predicate(map[cell])}

        //return map.any{mapi -> predicate(mapi.value)}
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun all(predicate: (T?) -> Boolean): Boolean {
        if (cells.any{map[it]== null})return false

        return map.all{mapi->predicate(mapi.value)}
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

