import java.util.*;

private fun readInt(): Int = readln().toInt();
private fun readInts(): List<Int> = readln().split(" ").map { it.toInt(); }
private fun readIntsMut(): MutableList<Int> = readInts().toMutableList();
private fun readLong(): Long = readln().toLong();
private fun readLongs(): List<Long> = readln().split(" ").map { it.toLong(); }
private fun readStrings(): List<String> = readln().split(" ");
private fun readBigInt(radix: Int = 10) = readln().toBigInteger(radix);

/* maybe when i get good at cp i should put like a cool quote here */

var ans2 = 0;
var countTable = IntArray(0);

val matches = ArrayList<Int>();

fun main(args: Array<String>) {
    var ans1 = 0;
    var line = readLine();
    matches.add(-1); //takes up 0th slot

    while (line != null) {
        val s = line.substring(line.indexOf(":") + 2);
        val wins = ArrayList<Int>();
        val have = ArrayList<Int>();

        val scan = Scanner(s);
        var addTo = wins;
        while (scan.hasNext()) {
            try {
                addTo.add(scan.next().toInt());
            } catch (e: NumberFormatException) {
                addTo = have;
                continue;
            }
        }

        //pt 1
        val matchCount = findMatches(wins, have);
        matches.add(matchCount);
        if (matchCount > 0) {
            ans1 += 1 shl (matchCount - 1);
        }

        line = readLine();
    }

    println(ans1);

    //pt 2

    countTable = IntArray(matches.size) { -1 };
    try {
        for (n in 1..<matches.size) {
            ans2 += countCards(n);
        }
    } catch (e: StackOverflowError) {
        println("oopsie overflow");
    }
    println(ans2);
}

fun findMatches(wins: ArrayList<Int>, have: ArrayList<Int>): Int {
    var ct = 0;
    for (x in have) {
        if (x in wins) {
            ct++;
        }
    }
    return ct;
}


fun countCards(n: Int): Int {
    if (n !in countTable.indices) {
        return 0;
    }
    
    if (countTable[n] != -1) {
        return countTable[n];
    }

    var x = matches[n];
    var count = 1;
    while (x > 0) {
        count += countCards(n + x);
        x--;
    }

    countTable[n] = count;
    return count;
}