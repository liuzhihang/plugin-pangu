package run.halo.starter;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.model.IModel;
import org.thymeleaf.model.IModelFactory;
import org.thymeleaf.processor.element.IElementModelStructureHandler;
import reactor.core.publisher.Mono;
import run.halo.app.plugin.SettingFetcher;
import run.halo.app.theme.dialect.TemplateHeadProcessor;

/**
 * tocbot 插件
 *
 * @author liuzhihang
 * @date 2022/10/23
 */
@Component
public class PanGuHeadProcessor implements TemplateHeadProcessor {

    private final SettingFetcher settingFetcher;

    public PanGuHeadProcessor(SettingFetcher settingFetcher) {
        this.settingFetcher = settingFetcher;
    }

    @Override
    public Mono<Void> process(ITemplateContext context, IModel model, IElementModelStructureHandler structureHandler) {
        return settingFetcher.fetch("basic", BasicConfig.class)
                .map(config -> {
                    final IModelFactory modelFactory = context.getModelFactory();
                    model.add(modelFactory.createText(tocbotScript(config)));
                    return Mono.empty();
                }).orElse(Mono.empty()).then();
    }

    private String tocbotScript(BasicConfig config) {

        // language=html
        return """
                <script src="/plugins/PluginPanGu/assets/static/pangu/4.0.7/pangu.min.js"></script>
                <script>
                    %s
                    document.addEventListener("DOMContentLoaded",  function() {
                       pangu.autoSpacingPage();
                    })
                </script>
                                
                """.formatted(config.getContent());
    }


    /**
     * 基础配置
     */
    @Data
    public static class BasicConfig {

        /**
         * 配置
         */
        String content;
    }
}
