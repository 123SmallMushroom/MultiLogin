package moe.caa.multilogin.core.auth.validate.entry;

import lombok.SneakyThrows;
import moe.caa.multilogin.api.logger.LoggerProvider;
import moe.caa.multilogin.core.auth.validate.ValidateContext;
import moe.caa.multilogin.core.main.MultiCore;
import moe.caa.multilogin.flows.workflows.BaseFlows;
import moe.caa.multilogin.flows.workflows.Signal;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.UUID;

public class InitialInGameUUIDFlows extends BaseFlows<ValidateContext> {
    private final MultiCore core;

    public InitialInGameUUIDFlows(MultiCore core) {
        this.core = core;
    }

    @SneakyThrows
    @Override
    public Signal run(ValidateContext validateContext) {

        // 首先从数据库里面读 UUID
        UUID inGameUUID = core.getSqlManager().getUserDataTable().getInGameUUID(
                validateContext.getYggdrasilAuthenticationResult().getResponse().getId(),
                validateContext.getYggdrasilAuthenticationResult().getYggdrasilId()
        );
        // 如果这个 UUID 不存在，表示是个新玩家。
        if (inGameUUID == null) {

            inGameUUID = validateContext.getYggdrasilAuthenticationResult().getYggdrasilServiceConfig().getInitUUID()
                    .generateUUID(validateContext.getYggdrasilAuthenticationResult().getResponse().getId(),
                            validateContext.getYggdrasilAuthenticationResult().getResponse().getName());
            // 尝试生成 UUID
            boolean inserted = false;
            do {
                try {
                    core.getSqlManager().getInGameProfileTable().insertNewData(inGameUUID);
                    inserted = true;
                } catch (SQLIntegrityConstraintViolationException ignored) {
                    inGameUUID = UUID.randomUUID();
                }
            } while (!inserted);

            core.getSqlManager().getUserDataTable().insertNewData(
                    validateContext.getYggdrasilAuthenticationResult().getResponse().getId(),
                    validateContext.getYggdrasilAuthenticationResult().getYggdrasilId(),
                    inGameUUID
            );
            LoggerProvider.getLogger().info(String.format("New profile: online uuid: %s, yggdrasil id: %d, in game uuid: %s.", validateContext.getYggdrasilAuthenticationResult().getResponse().getId().toString(),
                    validateContext.getYggdrasilAuthenticationResult().getYggdrasilId(), inGameUUID.toString()
            ));
        } else {
            // 检查一下数据存不存在，不存在就新建
            try {
                core.getSqlManager().getInGameProfileTable().insertNewData(inGameUUID);
            } catch (SQLIntegrityConstraintViolationException ignored) {
            }
        }
        validateContext.getInGameProfile().setId(inGameUUID);
        return Signal.PASSED;
    }
}