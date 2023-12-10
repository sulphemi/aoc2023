private fun readInt(): Int = readln().toInt();
private fun readLong(): Long = readln().toLong();
private fun readInts(): ArrayList<Int> = readln().split(" ").map { it.toInt() } as ArrayList<Int>;
private fun readLongs(): ArrayList<Long> = readln().split(" ").map { it.toLong() } as ArrayList<Long>;
private fun readStrings(): ArrayList<String> = readln().split(" ") as ArrayList<String>;
private fun readBigInt(radix: Int = 10) = readln().toBigInteger(radix);
private fun gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b);
private fun lcm(a: Int, b: Int): Int = (a * b) / gcd(a, b);
private fun gcd(a: Long, b: Long): Long = if (b == 0L) a else gcd(b, a % b);
private fun lcm(a: Long, b: Long): Long = (a * b) / gcd(a, b);

/* maybe when i get good at cp i should put like a cool quote here */

//oh yeah and please manually confirm what type of pipe S is
val s_type = '|';
//oh yeah btw you have to run this with -Xss1G or it might blow your stack

val maze = ArrayList<String>();
val steps = ArrayList<IntArray>();

fun main(args: Array<String>) {
    var line = readLine();
    while (line != null) {
        maze.add(line);
        steps.add(IntArray(line.length) { -1 });
        line = readLine();
    }

    //pt 1
    for (i in maze.indices) {
        for (k in maze[i].indices) {
            if (maze[i][k] == 'S') {
                thing(i, k, 0);
            }
        }
    }

    /* this doesn't work
    var highest = 0;
    for (row in steps) {
        for (x in row) {
            if (x > highest) highest = x;
        }
    }
    println(highest);
    */

    //...but this does for some reason
    var numCt = 0;
    for (row in steps) {
        for (x in row) {
            if (x != -1) numCt++;
        }
    }

    println(numCt / 2);

    //pt 2
    var thingyCt = 0;
    for (r in steps.indices) {
        val row = steps[r];
        val string = maze[r];

        var pipeCt = 0;
        var i = 0;
        while (i < row.size) {
            if (row[i] != -1) {
                var bend: Char;
                if (string[i] == 'S') {
                    bend = s_type;
                } else {
                    bend = string[i];
                }
                when (bend) {
                    'F' -> {
                        while (string[++i] !in "J7");
                        if (string[i] == 'J') pipeCt++;
                    }
                    'L' -> {
                        while (string[++i] !in "J7");
                        if (string[i] == '7') pipeCt++;
                    }
                    '|' -> pipeCt++;
                }
            } else {
                if (pipeCt % 2 == 1) {
                    thingyCt++;
                }
            }
            i++;
        }
    }

    println(thingyCt);
}


var startVisited = false;
fun thing(r: Int, c: Int, s: Int) {
    try {
        if (steps[r][c] != -1 && s > steps[r][c]) return;
    } catch (e: IndexOutOfBoundsException) {
        return;
    }

    if (maze[r][c] !in "-|JL7F") {
        if (startVisited) return;
        startVisited = true;
        steps[r][c] = 0;
        thing(r - 1, c, s + 1);
        thing(r + 1, c, s + 1);
        thing(r, c - 1, s + 1);
        thing(r, c + 1, s + 1);
        return;
    }

    steps[r][c] = s;

    when (maze[r][c]) {
        '-' -> {
            thing(r, c - 1, s + 1);
            thing(r, c + 1, s + 1);
        }
        '|' -> {
            thing(r - 1, c, s + 1);
            thing(r + 1, c, s + 1);
        }
        'J' -> {
            thing(r, c - 1, s + 1);
            thing(r - 1, c, s + 1);
        }
        'L' -> {
            thing(r, c + 1, s + 1);
            thing(r - 1, c, s + 1);
        }
        '7' -> {
            thing(r, c - 1, s + 1);
            thing(r + 1, c, s + 1);
        }
        'F' -> {
            thing(r, c + 1, s + 1);
            thing(r + 1, c, s + 1);
        }
    }
}