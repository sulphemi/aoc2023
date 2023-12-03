import java.util.*;

private fun readInt(): Int = readln().toInt();
private fun readInts(): List<Int> = readln().split(" ").map { it.toInt(); }
private fun readIntsMut(): MutableList<Int> = readInts().toMutableList();
private fun readLong(): Long = readln().toLong();
private fun readLongs(): List<Long> = readln().split(" ").map { it.toLong(); }
private fun readStrings(): List<String> = readln().split(" ");
private fun readBigInt(radix: Int = 10) = readln().toBigInteger(radix);

/* maybe when i get good at cp i should put like a cool quote here */

val grid = ArrayList<CharArray>();
var valid: Array<CharArray> = Array(0) { CharArray(0) };
val restoreStack = Stack<Triple<Char, Int, Int>>();

fun main(args: Array<String>) {
    var line = readLine();
    while (line != null) {
        grid.add(line.toCharArray());
        line = readLine();
    }

    valid = Array(grid.size) { CharArray(grid[0].size) {' '} };
    for (row in grid.indices) {
        for (col in grid[row].indices) {
            if (isSymbol(grid[row][col])) {
                seek(row - 1, col - 1);
                seek(row - 1, col);
                seek(row - 1, col + 1);

                seek(row, col - 1);
                seek(row, col + 1);

                seek(row + 1, col - 1);
                seek(row + 1, col);
                seek(row + 1, col + 1);
            }
        }
    }

    var allNumbers = StringBuilder();
    for (x in valid) {
        allNumbers.append(x);
        allNumbers.append(' ');
    }

    val stupidScanner = Scanner(allNumbers.toString());
    var ans1 = 0;

    while (stupidScanner.hasNextInt()) {
        ans1 += stupidScanner.nextInt();
    }

    println(ans1);

    /*** *** ***/

    var ans2 = 0;
    for (row in grid.indices) {
        for (col in grid[row].indices) {
            if (grid[row][col] == '*') {
                val numbers = ArrayList<Int>();
                numbers.add(grabNumber(row - 1, col - 1));
                numbers.add(grabNumber(row - 1, col));
                numbers.add(grabNumber(row - 1, col + 1));

                numbers.add(grabNumber(row, col - 1));
                numbers.add(grabNumber(row, col + 1));

                numbers.add(grabNumber(row + 1, col - 1));
                numbers.add(grabNumber(row + 1, col));
                numbers.add(grabNumber(row + 1, col + 1));

                restore();

                while (-1 in numbers) numbers.remove(-1);
                if (numbers.size == 2) ans2 += numbers[0] * numbers[1];
            }
        }
    }


    println(ans2);
}

fun seek(x: Int, y: Int) {
    try {
        if (grid[x][y] in '0'..'9') {
            if (valid[x][y] != ' ') return;
            valid[x][y] = grid[x][y];
            seek(x, y - 1);
            seek(x, y + 1);
        } else {
            return;
        }
    } catch (e: Exception) {
        //should be arrayindexoob
    }
}

fun grabNumber(x: Int, y: Int): Int {
    if (grid[x][y] !in '0'..'9') return -1;
    var n = "" + snatch(x, y);
    var _y = y - 1;
    while (_y >= 0 && grid[x][_y] in '0'..'9') {
        n = "" + snatch(x, _y) + n;
        _y--;
    }

    _y = y + 1;
    while (_y < grid[0].size && grid[x][_y] in '0'..'9') {
        n += snatch(x, _y);
        _y++;
    }

    return n.toInt();
}

fun snatch(x: Int, y: Int): Char {
    val v = grid[x][y];
    restoreStack.push(Triple(v, x, y));
    grid[x][y] = 'x';
    return v;
}

fun restore() {
    while (! restoreStack.empty()) {
        val (v, x, y) = restoreStack.pop();
        grid[x][y] = v;
    }
}

fun isSymbol(c: Char): Boolean {
    return (c != '.') && (c !in '0'..'9');
}