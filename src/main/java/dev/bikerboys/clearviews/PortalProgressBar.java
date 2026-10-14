package dev.bikerboys.clearviews;

//? if >=1.21.11 {
import dev.bikerboys.clearviews.config.ClearviewsConfig;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Portal;

public final class PortalProgressBar {
    private static final int BAR_WIDTH = 182;
    private static final int BAR_HEIGHT = 5;
    private static final int BACKGROUND_COLOR = 0xFF3A3A3A;
    private static final int BORDER_COLOR = 0xFF000000;
    private static final int FILL_COLOR = 0xFFB14DFF;
    private static final int TEXT_COLOR = 0xFFFFFFFF;

    private static int ticksInsidePortal = 0;

    private PortalProgressBar() {
    }

    public static void register() {
        HudElementRegistry.addLast(
                Identifier.fromNamespaceAndPath("clearviews", "portal_progress"),
                PortalProgressBar::render);
    }

    public static void tick(Minecraft mc) {
        LocalPlayer player = mc.player;
        if (player == null
                || player.getActivePortalLocalTransition() != Portal.Transition.CONFUSION
                || player.portalEffectIntensity <= player.oPortalEffectIntensity) {
            ticksInsidePortal = 0;
            return;
        }

        ticksInsidePortal++;
    }

    private static void render(GuiGraphics graphics, DeltaTracker deltaTracker) {
        ClearviewsConfig config = ClearviewsConfig.CONFIG.instance();
        if (!config.portalProgressBar || ticksInsidePortal <= 0) {
            return;
        }

        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || mc.options.hideGui) {
            return;
        }

        float progress = Math.min(ticksInsidePortal / (float) Math.max(1, config.portalProgressTicks), 1.0F);

        int x = (graphics.guiWidth() - BAR_WIDTH) / 2;
        int y = graphics.guiHeight() - 60;

        graphics.fill(x - 1, y - 1, x + BAR_WIDTH + 1, y + BAR_HEIGHT + 1, BORDER_COLOR);
        graphics.fill(x, y, x + BAR_WIDTH, y + BAR_HEIGHT, BACKGROUND_COLOR);

        int filled = Math.round(BAR_WIDTH * progress);
        if (filled > 0) {
            graphics.fill(x, y, x + filled, y + BAR_HEIGHT, FILL_COLOR);
        }

        Font font = mc.font;
        Component label = Component.literal("Teleporting... " + Math.round(progress * 100.0F) + "%");
        graphics.drawString(font, label,
                (graphics.guiWidth() - font.width(label)) / 2,
                y - font.lineHeight - 3,
                TEXT_COLOR, true);
    }
}
//?}
