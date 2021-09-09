package moe.caa.multilogin.core.impl;

/**
 * 代表一个服务器对象
 */
public interface IServer {

    /**
     * 获得服务器核心名称
     *
     * @return 服务器核心名称
     */
    String getName();

    /**
     * 获得服务器版本
     *
     * @return 服务器版本
     */
    String getVersion();

    /**
     * 获得线程调度器对象
     *
     * @return 线程调度器对象
     */
    AbstractScheduler getScheduler();

    /**
     * 获得玩家管理器
     *
     * @return 获得玩家管理器
     */
    IPlayerManager getPlayerManager();

    /**
     * 通过字符串获得验证结果对象
     *
     * @param input 字符串
     * @return 验证结果
     */
    <T> T getAuthResult(String input);

    /**
     * 关闭服务器
     */
    void shutdown();
}
