package org.telegram.messenger;

public class MessageReplacer {
    
    // 消息替换开关
    public static boolean enableReplace = true;
    
    // 固定替换文本
    public static String replaceText = "这是一条替换后的消息";
    
    // 是否使用正则替换模式
    public static boolean useRegex = false;
    public static String regexPattern = "";
    public static String regexReplacement = "";
    
    /**
     * 替换消息内容
     * @param originalMessage 原始消息内容
     * @return 替换后的消息内容
     */
    public static String replaceMessage(String originalMessage) {
        if (!enableReplace || originalMessage == null || originalMessage.isEmpty()) {
            return originalMessage;
        }
        
        if (useRegex && !regexPattern.isEmpty()) {
            try {
                return originalMessage.replaceAll(regexPattern, regexReplacement);
            } catch (Exception e) {
                // 正则表达式出错时返回原文
                return originalMessage;
            }
        }
        
        // 固定文本替换模式
        return replaceText;
    }
}
