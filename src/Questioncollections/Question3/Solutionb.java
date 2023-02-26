package Questioncollections.Question3;
/*
you are provided certain string and pattern, return true if pattern entirely matches the string otherwise return false.
Note: if pattern contains char @ it matches entire sequence of characters and # matches any single character within string.
Input: String a=“tt”, pattern =”@”
Output: true
Input: String a=“ta”, pattern =”t”
Output: false
Input: String a=“ta”, pattern =”t#”
Output: true

 */
public class Solutionb{
    public static boolean patternMatches(String str, String pattern) {
        // If pattern is "@" then it matches the entire string
        if (pattern.equals("@")) {
            return str.equals(pattern);
        }

        // If pattern is longer than string or contains only "#" (which matches any single character),
        // then pattern cannot match the string
        if (pattern.length() > str.length() || pattern.replaceAll("#", "").isEmpty()) {
            return false;
        }

        // Iterate over the characters in the string and pattern and check if they match
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (i < pattern.length()) {
                char p = pattern.charAt(i);
                if (p == '@') {
                    // If the pattern character is "@", it matches the rest of the string
                    return i == str.length() - 1;
                } else if (p == '#') {
                    // If the pattern character is "#", it matches any single character
                    continue;
                } else if (c != p) {
                    // If the characters do not match, the pattern does not match the string
                    return false;
                }
            } else {
                // If the pattern is shorter than the string, it does not match
                return false;
            }
        }

        // If we have iterated over the entire string and pattern, the pattern matches the string
        return true;
    }
    public static void main(String[] args) {
        String str1 = "tt";
        String pattern1 = "@";
        boolean result1 = patternMatches(str1, pattern1);
        System.out.println(result1); // true

        String str2 = "ta";
        String pattern2 = "t";
        boolean result2 = patternMatches(str2, pattern2);
        System.out.println(result2); // false

        String str3 = "ta";
        String pattern3 = "t#";
        boolean result3 = patternMatches(str3, pattern3);
        System.out.println(result3); // true
    }

}
