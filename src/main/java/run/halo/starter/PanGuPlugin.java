package run.halo.starter;

import org.pf4j.PluginWrapper;
import org.springframework.stereotype.Component;
import run.halo.app.plugin.BasePlugin;

/**
 * pangu js 集成
 *
 * @author liuzhihang
 * @date 2022/10/23
 */
@Component
public class PanGuPlugin extends BasePlugin {

    public PanGuPlugin(PluginWrapper wrapper) {
        super(wrapper);
    }

    @Override
    public void start() {
    }

    @Override
    public void stop() {
    }
}
