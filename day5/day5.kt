import java.util.*;

private fun readInt(): Int = readln().toInt();
private fun readLong(): Long = readln().toLong();
private fun readInts(): ArrayList<Int> = readln().split(" ").map { it.toInt() } as ArrayList<Int>;
private fun readLongs(): ArrayList<Long> = readln().split(" ").map { it.toLong() } as ArrayList<Long>;
private fun readStrings(): ArrayList<String> = readln().split(" ") as ArrayList<String>;
private fun readBigInt(radix: Int = 10) = readln().toBigInteger(radix);

/* maybe when i get good at cp i should put like a cool quote here */

val seeds = ArrayList<Long>();
val convertTables = ArrayList<ArrayList<ArrayList<Long>>>();

fun main() {
    readInput();

    //pt 1
    val locationNumbers = ArrayList<Long>(seeds.size);

    for (s in seeds) {
        var x = s;
        for (T in convertTables) {
            for (triple in T) {
                if (x > triple[1] && x < triple[1] + triple[2]) {
                    x = x - triple[1] + triple[0];
                    break;
                }
            }
        }
        locationNumbers.add(x);
    }

    println(locationNumbers.minOrNull());


    //pt 2
    var newRanges = ArrayList<LongArray>();
    var newerRanges = ArrayList<LongArray>();
    val toAdd = Stack<LongArray>();
    for (i in seeds.indices step 2) {
        newRanges.add(longArrayOf(seeds[i], seeds[i] + seeds[i + 1] - 1));
    }

    for (T in convertTables) {
        //println("---");
        for (rule in T) {
            val ruleLeft = rule[1];
            val ruleRight = rule[1] + rule[2] - 1;
            val shift = rule[0] - rule[1];

            //println();
            //println("rule: [$ruleLeft, $ruleRight]");

            for (r in newRanges) {
                //println(Arrays.toString(r));

                if (r[0] == -1L) continue; //range is empty
                if (r[1] < r[0]) {
                    println("oh noes!");
                    continue;
                }

                //both left and right bounds of rule inside range
                if (r[0] < ruleLeft && ruleRight < r[1]) {
                    //println("both left and right bounds of rule inside range");
                    //add left and right of range unshifted

                    //add center shifted
                    newerRanges.add(longArrayOf(ruleLeft + shift, ruleRight + shift));

                    //modify current to be left range
                    r[1] = ruleLeft - 1;

                    //also add right range
                    toAdd.push(longArrayOf(ruleRight + 1, r[1]));
                }

                //left outside range, right in
                else if (ruleLeft <= r[0] && ruleRight < r[1] && ruleRight > r[0]) {
                    //println("left outside range, right in");
                    //left range shifted, right range unshifted

                    //add left range
                    newerRanges.add(longArrayOf(r[0] + shift, ruleRight + shift));

                    //modify to be right range
                    r[0] = ruleRight + 1;
                }

                //left in range, right out
                else if (r[0] < ruleLeft && ruleLeft < r[1] && r[1] <= ruleRight) {
                    //println("left in range, right out");
                    //left range unshifted, right range shifted

                    //add right range
                    newerRanges.add(longArrayOf(ruleLeft + shift, r[1] + shift));

                    //modify to be left range
                    r[1] = ruleLeft - 1;
                }

                //rule emcompasses whole range
                else if (ruleLeft <= r[0] && r[1] <= ruleRight) {
                    //println("rule emcompasses whole range");
                    //add entire range, shifted
                    newerRanges.add(longArrayOf(r[0] + shift, r[1] + shift));

                    //mark range as empty
                    r[0] = -1L;
                }

                //rule does not intersect with range
                else if (ruleRight < r[0] || r[1] < ruleLeft) {
                    //println("rule does not intersect");
                    //do nothing
                }

                else {
                    println("something that wasn't supposed to happen happened");
                }
            } //end of ranges
            while (! toAdd.empty()) newRanges.add(toAdd.pop());
        } //end of rules

        for (r in newRanges) {
            if (r[0] != -1L) {
                newerRanges.add(r);
            }
        }

        newRanges = newerRanges;
        newerRanges = ArrayList<LongArray>();
    }

    var minVal = newRanges[0][0];
    for (r in newRanges) {
        //println(Arrays.toString(r));
        if (r[0] < minVal) {
            minVal = r[0];
        }
    }

    println(minVal);
}

fun readInput() {
    var s = readln();
    s = s.substring(s.indexOf(":") + 2);
    s.split(" ").map { it.toLong() }.forEach { seeds.add(it) };

    readln(); //blank
    readln(); //header

    var addingTo = ArrayList<ArrayList<Long>>();
    var line = readLine();
    while (line != null) {
        if (line == "") {
            convertTables.add(addingTo);
            addingTo = ArrayList<ArrayList<Long>>();

            readLine(); //discard header
            line = readLine();
            continue;
        }

        addingTo.add(line.split(" ").map { it.toLong() } as ArrayList<Long>);
        line = readLine();
    }
}