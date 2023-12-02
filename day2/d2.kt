private fun readInt(): Int = readln().toInt();
private fun readInts(): List<Int> = readln().split(" ").map { it.toInt(); }
private fun readIntsMut(): MutableList<Int> = readInts().toMutableList();
private fun readLong(): Long = readln().toLong();
private fun readLongs(): List<Long> = readln().split(" ").map { it.toLong(); }
private fun readStrings(): List<String> = readln().split(" ");
private fun readBigInt(radix: Int = 10) = readln().toBigInteger(radix);

/* maybe when i get good at cp i should put like a cool quote here */

var have = arrayOf(12, 13, 14);
var ans = 0;

fun main1() {
    var line = readLine();
    while (line != null) {
        val code = line.substring(line.indexOf(" ") + 1, line.indexOf(":")).toInt();
        val s = line.substring(line.indexOf(":") + 2);

        if (p1(s)) {
            ans += code;
        }

        line = readLine();
    }

    println(ans);
}

fun color2ind(s: String): Int {
    return when (s) {
        "red" -> 0;
        "green" -> 1;
        "blue" -> 2;
        else -> 69420;
    }
}

fun p1(s: String): Boolean {
    val a = s.split("; ").map { it.split(", ") };
    for (i in a) {
        val req = arrayOf(0, 0, 0);
        for (k in i) {
            val pair = k.split(" ");
            val n = pair[0].toInt();
            val c = pair[1];

            req[color2ind(c)] += n;
        }

        for (x in have.indices) {
            if (have[x] < req[x]) {
                return false;
            }
        }
    }

    return true;
}

fun main() {
    var line = readLine();
    while (line != null) {
        val code = line.substring(line.indexOf(" ") + 1, line.indexOf(":")).toInt();
        val s = line.substring(line.indexOf(":") + 2);

        ans += p2(s);

        line = readLine();
    }

    println(ans);
}

fun p2(s: String): Int {
    val min = arrayOf(0, 0, 0);
    val a = s.split("; ").map { it.split(", ") };
    for (i in a) {
        val req = arrayOf(0, 0, 0);
        for (k in i) {
            val pair = k.split(" ");
            val n = pair[0].toInt();
            val c = pair[1];

            req[color2ind(c)] += n;
        }

        for (x in min.indices) {
            if (min[x] < req[x]) {
                min[x] = req[x];
            }
        }
    }

    return min[0] * min[1] * min[2];
}