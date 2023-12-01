private fun readInt(): Int = readln().toInt();
private fun readInts(): List<Int> = readln().split(" ").map { it.toInt(); }
private fun readIntsMut(): MutableList<Int> = readInts().toMutableList();
private fun readLong(): Long = readln().toLong();
private fun readLongs(): List<Long> = readln().split(" ").map { it.toLong(); }
private fun readStrings(): List<String> = readln().split(" ");
private fun readBigInt(radix: Int = 10) = readln().toBigInteger(radix);

/* maybe when i get good at cp i should put like a cool quote here */

fun _main(args: Array<String>) {
    var sum = 0;
    var line = readLine();
    while (line != null) {
        var s = "";
        for (c in thingy(line)) {
            if (c in '0'..'9') {
                s += c
            }
        }

        //println(s)

        val first = "" + s[0];
        val last = "" + s[s.length - 1]

        sum += first.toInt() * 10
        sum += last.toInt()
        line = readLine();
    }

    println(sum)
}

fun thingy(s: String): String {
    val sa = s.toCharArray();
    val a = arrayOf("zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine");

    var r = 1;
    //println("reached")
    while (r > 0) {
        r = 0;
        for (w in a.indices) {
            val ind = StringBuilder().append(sa).toString().indexOf(a[w])
            if (ind > -1) {
                sa[ind] = '0' + w;
                sa[ind + a[w].length - 1] = 'z'
                r++;
            }
        }
        //println(r)
    }
    //println("elohel")

    return StringBuilder().append(sa).toString();
}

fun main() {
    println(thingy(readln()));
}