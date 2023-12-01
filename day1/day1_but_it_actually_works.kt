val map = arrayOf("zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine");

fun main() {
    var line = readLine();
    var sum = 0;
    while (line != null) {
        val nums = ArrayList<Int>();
        while (line.length > 0) {
            if (line[0] in '0'..'9') {
                nums.add(line[0] - '0');
            } else {
                for (m in map.indices) {
                    if (m == 0) continue;
                    if (line!!.startsWith(map[m])) {
                        nums.add(m);
                    }
                }
            }
            line = line.substring(1);
        }
        //println(nums[0] * 10 + nums[nums.size - 1]);
        sum += nums[0] * 10 + nums[nums.size - 1];
        line = readLine();
    }

    println(sum);
}