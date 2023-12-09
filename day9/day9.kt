import java.util.*;

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

val histories = ArrayList<ArrayList<Int>>();

fun main(args: Array<String>) {
    var line = readLine();
    while (line != null) {
        histories.add(line.split(" ").map { it.toInt() } as ArrayList<Int>);
        line = readLine();
    }

    //pt 1
    var ans1 = 0;
    for (h in histories) {
        val hstack = Stack<Int>();
        var diff = makeDiff(h);
        while (! allZero(diff)) {
            hstack.push(diff[diff.size - 1]);
            diff = makeDiff(diff);
        }
        
        var next = h[h.size - 1];
        while (! hstack.empty()) next += hstack.pop();
        ans1 += next;
    }

    println(ans1);

    //pt 2
    var ans2 = 0;
    for (h in histories) {
        val hstack = Stack<Int>();
        val otherstack = Stack<Int>();
        var diff = makeDiff(h);
        while (! allZero(diff)) {
            hstack.push(diff[0]);
            diff = makeDiff(diff);
        }

        var prevP = 0; //always 0
        while (! hstack.empty()) {
            val k = hstack.pop();
            otherstack.push(k - prevP);
            prevP = k - prevP;
        }

        var prev = h[0];
        prev -= otherstack.pop();
        ans2 += prev;
    }

    println(ans2);
}

fun allZero(l: ArrayList<Int>): Boolean {
    for (x in l) {
        if (x != 0) {
            return false;
        }
    }
    return true;
}

fun makeDiff(l: ArrayList<Int>): ArrayList<Int> {
    val diff = ArrayList<Int>(l.size - 1);
    for (i in l.indices) {
        if (i == 0) continue;
        diff.add(l[i] - l[i - 1]);
    }
    return diff;
}