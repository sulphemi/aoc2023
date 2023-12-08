private fun readInt(): Int = readln().toInt();
private fun readLong(): Long = readln().toLong();
private fun readInts(): ArrayList<Int> = readln().split(" ").map { it.toInt() } as ArrayList<Int>;
private fun readLongs(): ArrayList<Long> = readln().split(" ").map { it.toLong() } as ArrayList<Long>;
private fun readStrings(): ArrayList<String> = readln().split(" ") as ArrayList<String>;
private fun readBigInt(radix: Int = 10) = readln().toBigInteger(radix);

/* maybe when i get good at cp i should put like a cool quote here */

val nodes = HashMap<String, Pair<String, String>>();
var directions = "meow";

fun main(args: Array<String>) {
    directions = readln();
    readln();

    var line = readLine();
    while (line != null) {
        val n = line.substring(0, 3);
        val _l = line.substringAfter("(").split(", ");
        val _left = _l[0];
        val _right = _l[1].substring(0, 3);
        nodes.put(n, Pair(_left, _right));
        line = readLine();
    }

    //pt 1

    println(countStepsPt1());

    //pt 2

    val ghostSteps = ArrayList<Long>();
    for (n in nodes.keys) {
        if (n.endsWith('A')) {
            ghostSteps.add(countSteps(n));
        }
    }

    var ans2 = 1L;
    for (x in ghostSteps) {
        ans2 = lcm(ans2, x);
    }
    println(ans2);
}


fun countStepsPt1(): Int {
    var dirindex = 0;
    var steps = 0;
    var current = "AAA";
    while (current != "ZZZ") {
        val d = directions[dirindex++];
        if (dirindex >= directions.length) dirindex = 0;

        if (d == 'L') {
            current = nodes[current]!!.first;
        } else if (d == 'R') {
            current = nodes[current]!!.second;
        } else {
            println("aaaaa");
        }

        steps++;
    }
    return steps;
}

fun countSteps(start: String): Long {
    var dirindex = 0;
    var steps = 0;
    var current = start;
    while (! current.endsWith('Z')) {
        val d = directions[dirindex++];
        if (dirindex >= directions.length) dirindex = 0;

        if (d == 'L') {
            current = nodes[current]!!.first;
        } else if (d == 'R') {
            current = nodes[current]!!.second;
        } else {
            println("aaaaa");
        }

        steps++;
    }
    return steps.toLong();
}

//wtf why isnt this in neither java.math nor kotlin.math
fun lcm(_a: Long, _b: Long): Long {
    val a = Math.max(_a, _b);
    val b = _a + _b - a;
    var lcm = a;

    while (lcm % a != 0L || lcm % b != 0L) {
        lcm += a;
    }
    return lcm;
}