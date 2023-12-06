import java.util.*;

private fun readInt(): Int = readln().toInt();
private fun readLong(): Long = readln().toLong();
private fun readInts(): ArrayList<Int> = readln().split(" ").map { it.toInt() } as ArrayList<Int>;
private fun readLongs(): ArrayList<Long> = readln().split(" ").map { it.toLong() } as ArrayList<Long>;
private fun readStrings(): ArrayList<String> = readln().split(" ") as ArrayList<String>;
private fun readBigInt(radix: Int = 10) = readln().toBigInteger(radix);

/* maybe when i get good at cp i should put like a cool quote here */

val times = ArrayList<Int>();
val dists = ArrayList<Int>();

fun main() {
    //read input
    val tscan = Scanner(readln());
    tscan.next();
    while (tscan.hasNextInt()) times.add(tscan.nextInt());
    val dscan = Scanner(readln());
    dscan.next();
    while (dscan.hasNextInt()) dists.add(dscan.nextInt());

    //pt 1
    val ways = IntArray(times.size);
    for (i in times.indices) {
        for (n in 0..times[i]) {
            if (n * (times[i] - n) > dists[i]) {
                ways[i]++;
            }
        }
    }

    var ans1 = 1;
    for (n in ways) ans1 *= n;
    println(ans1);

    //pt 2
    var actualTimeStr = "";
    var actualDistStr = "";
    for (i in times.indices) {
        actualDistStr += dists[i];
        actualTimeStr += times[i];
    }

    val time = actualTimeStr.toLong();
    val dist = actualDistStr.toLong();

    val a = -1;
    val b = time.toDouble();
    val c = (-dist).toDouble();

    val lowerBound = Math.floor((-b - Math.sqrt(b*b - 4*a*c)) / 2);
    val upperBound = Math.floor((-b + Math.sqrt(b*b - 4*a*c)) / 2);

    println((upperBound - lowerBound).toLong());

    // var ans2 = 0;
    // for (n in 0..time) {
    //     if (n * (time - n) > dist) {
    //         ans2++;
    //     }
    // }
    // println(ans2);
}