private fun readInt(): Int = readln().toInt();
private fun readLong(): Long = readln().toLong();
private fun readInts(): ArrayList<Int> = readln().split(" ").map { it.toInt() } as ArrayList<Int>;
private fun readLongs(): ArrayList<Long> = readln().split(" ").map { it.toLong() } as ArrayList<Long>;
private fun readStrings(): ArrayList<String> = readln().split(" ") as ArrayList<String>;
private fun readBigInt(radix: Int = 10) = readln().toBigInteger(radix);

/* maybe when i get good at cp i should put like a cool quote here */

val cardValues = "AKQJT98765432".reversed();
val jokerValues = "J23456789TQKA";
val hands = ArrayList<Triple<String, Int, Int>>();
val jokerHands = ArrayList<Triple<String, Int, Int>>();

fun main(args: Array<String>) {
    var line = readLine();
    while (line != null) {
        val _l = line.split(" ");
        hands.add(Triple(_l[0], _l[1].toInt(), handStrength(_l[0]))); //card, wager, handStr
        line = readLine();
    }

    //pt 1
    hands.sortWith(compareBy(
        { it.third },
        { cardValues.indexOf(it.first[0]) },
        { cardValues.indexOf(it.first[1]) },
        { cardValues.indexOf(it.first[2]) },
        { cardValues.indexOf(it.first[3]) },
        { cardValues.indexOf(it.first[4]) }
    ));

    var ans1 = 0;
    for (i in hands.indices) {
        ans1 += (i + 1) * hands[i].second;
    }
    println(ans1);

    //pt 2
    for (h in hands) {
        jokerHands.add(Triple(h.first, h.second, jokerStrength(h.first)));
    }

    jokerHands.sortWith(compareBy(
        { it.third },
        { jokerValues.indexOf(it.first[0]) },
        { jokerValues.indexOf(it.first[1]) },
        { jokerValues.indexOf(it.first[2]) },
        { jokerValues.indexOf(it.first[3]) },
        { jokerValues.indexOf(it.first[4]) }
    ));

    var ans2 = 0;
    for (i in jokerHands.indices) {
        ans2 += (i + 1) * jokerHands[i].second;
    }
    println(ans2);
}

fun handStrength(card: String): Int {
    val cardCt = IntArray(cardValues.length);
    for (c in card) {
        cardCt[cardValues.indexOf(c)]++;
    }

    assert(cardCt.sum() == 5);
    
    if (5 in cardCt) {
        //five of a kind
        return 6;
    } else if (4 in cardCt) {
        //four of a kind
        return 5;
    } else if (2 in cardCt && 3 in cardCt) {
        //full house
        return 4;
    } else if (3 in cardCt) {
        //three of a kind
        return 3;
    } else if (2 in cardCt) {
        //pair, have to decide if one or two
        if (cardCt.indexOf(2) == cardCt.lastIndexOf(2)) {
            return 1;
        } else {
            return 2;
        }
    } else {
        return 0;
    }
}

fun jokerStrength(card: String): Int {
    val jokerCt = card.count { it == 'J' };
    if (jokerCt == 0) return handStrength(card);

    val cardCt = IntArray(jokerValues.length);
    for (c in card) {
        cardCt[jokerValues.indexOf(c)]++;
    }
    cardCt[0] = 0; //ignore jokers, cannot add jokers to jokers because they cant become jokers if they're already jokers if that makes sense. it doesnt. okay, what im trying to say is 

    //always optimal to add to max
    cardCt[cardCt.indexOf(cardCt.maxOrNull()!!)] += jokerCt;
    if (5 in cardCt) {
        //five of a kind
        return 6;
    } else if (4 in cardCt) {
        //four of a kind
        return 5;
    } else if (2 in cardCt && 3 in cardCt) {
        //full house
        return 4;
    } else if (3 in cardCt) {
        //three of a kind
        return 3;
    } else if (2 in cardCt) {
        //pair, have to decide if one or two
        if (cardCt.indexOf(2) == cardCt.lastIndexOf(2)) {
            return 1;
        } else {
            return 2;
        }
    } else {
        return 0;
    }
}