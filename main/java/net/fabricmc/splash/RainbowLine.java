package net.fabricmc.splash;



import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.client.gui.screen.CommandSuggestor;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.util.NarratorManager;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Style;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.math.MathHelper;
import java.awt.*;

//Copyright to Splash Client 2017-2020(©)
//This client is free to use, however copying/modifying its code is strictly forbidden.
//If you have any questions about fair use, contact Splash#7713 on Discord.

@Environment(EnvType.CLIENT)
public class RainbowLine extends Screen {
    private String field_2389 = "";
    private int messageHistorySize = -1;
    protected TextFieldWidget chatField;
    private String originalChatText = "";
    private CommandSuggestor commandSuggestor;
    MinecraftClient MC;

    public RainbowLine(Screen originalChatText) {
        super(NarratorManager.EMPTY);
        //this.originalChatText = originalChatText;
    }

    protected void init() {
        //this.client.keyboard.enableRepeatEvents(true);
        this.messageHistorySize = this.client.inGameHud.getChatHud().getMessageHistory().size();
        this.chatField = new TextFieldWidget(this.textRenderer, 4, this.height - 12, this.width - 4, 12, new TranslatableText("chat.editBox")) {  //chat.editBox

        };
        this.chatField.setMaxLength(256);
        this.chatField.setHasBorder(false);
        this.chatField.setText(this.originalChatText);
        this.chatField.setChangedListener(this::onChatFieldUpdate);
        this.children.add(this.chatField);
        this.commandSuggestor = new CommandSuggestor(this.client, this, this.chatField, this.textRenderer, false, false, 1, 10, true, -805306368);
        this.commandSuggestor.refresh();
        this.setInitialFocus(this.chatField);
    }

    public void resize(MinecraftClient client, int width, int height) {
        String string = this.chatField.getText();
        this.init(client, width, height);
        this.setText(string);
        this.commandSuggestor.refresh();
    }

    public void removed() {
        //this.client.keyboard.enableRepeatEvents(false);
        this.client.inGameHud.getChatHud().resetScroll();
    }

    public void tick() {
        this.chatField.tick();
    }

    private void onChatFieldUpdate(String chatText) {
        String string = this.chatField.getText();
        this.commandSuggestor.setWindowActive(!string.equals(this.originalChatText));
        this.commandSuggestor.refresh();
    }

    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (this.commandSuggestor.keyPressed(keyCode, scanCode, modifiers)) {
            return true;
        } else if (super.keyPressed(keyCode, scanCode, modifiers)) {
            return true;
        } else if (keyCode == 256) {
            this.client.openScreen((Screen)null);
            return true;
        } else if (keyCode != 257 && keyCode != 335) {
            if (keyCode == 265) {
                this.setChatFromHistory(-1);
                return true;
            } else if (keyCode == 264) {
                this.setChatFromHistory(1);
                return true;
            } else if (keyCode == 266) {
                this.client.inGameHud.getChatHud().scroll((double)(this.client.inGameHud.getChatHud().getVisibleLineCount() - 1));
                return true;
            } else if (keyCode == 267) {
                this.client.inGameHud.getChatHud().scroll((double)(-this.client.inGameHud.getChatHud().getVisibleLineCount() + 1));
                return true;
            } else {
                return false;
            }
        } else {
            String string = this.chatField.getText().trim();
            if (!string.isEmpty()) {

                StringBuffer str= new StringBuffer(string);
                str.insert(0,".&#ff0000");
                if(str.length()>9)
                str.insert(10,"&#ff4000");
                if(str.length()>18)
                str.insert(19,"&#ff8000");
                if(str.length()>27)
                str.insert(28,"&#ffbf00");
                if(str.length()>36)
                str.insert(37,"&#ffff00");
                if(str.length()>45)
                str.insert(46,"&#bfff00");
                if(str.length()>54)
                str.insert(55,"&#80ff00");
                if(str.length()>63)
                str.insert(64,"&#40ff00");
                if(str.length()>72)
                str.insert(73,"&#00ff00");
                if(str.length()>81)
                str.insert(82,"&#00ff40");
                if(str.length()>90)
                str.insert(91,"&#00ff80");
                if(str.length()>99)
                str.insert(100,"&#00ffbf");
                if(str.length()>108)
                    str.insert(109,"&#00ffff");
                if(str.length()>117)
                    str.insert(118,"&#00bfff");
                if(str.length()>126)
                    str.insert(127,"&#0080ff");
                if(str.length()>135)
                    str.insert(136,"&#0040ff");
                if(str.length()>144)
                    str.insert(145,"&#0000ff");
                if(str.length()>153)
                    str.insert(154,"&#4000ff");
                if(str.length()>162)
                    str.insert(163,"&#8000ff");
                if(str.length()>171)
                    str.insert(172,"&#bf00ff");
                if(str.length()>180)
                    str.insert(181,"&#ff00ff");
                if(str.length()>189)
                    str.insert(190,"&#ff00bf");
                if(str.length()>198)
                    str.insert(199,"&#ff0080");
                if(str.length()>207)
                    str.insert(208,"&#ff0040");
                if(str.length()>216)
                str.insert(217,"&#ff0000");
                if(str.length()>225)
                    str.insert(226,"&#ff4000");
                if(str.length()>234)
                    str.insert(235,"&#ff8000");
                if(str.length()>243)
                    str.insert(244,"&#ffbf00");

                this.sendMessage(String.valueOf(str)); //string

            }

            this.client.openScreen((Screen)null);
            return true;
        }
    }

    public boolean mouseScrolled(double mouseX, double mouseY, double amount) {
        if (amount > 1.0D) {
            amount = 1.0D;
        }

        if (amount < -1.0D) {
            amount = -1.0D;
        }

        if (this.commandSuggestor.mouseScrolled(amount)) {
            return true;
        } else {
            if (!hasShiftDown()) {
                amount *= 7.0D;
            }

            this.client.inGameHud.getChatHud().scroll(amount);
            return true;
        }
    }

    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (this.commandSuggestor.mouseClicked((double)((int)mouseX), (double)((int)mouseY), button)) {
            return true;
        } else {
            if (button == 0) {
                ChatHud chatHud = this.client.inGameHud.getChatHud();
                if (chatHud.mouseClicked(mouseX, mouseY)) {
                    return true;
                }

                Style style = chatHud.getText(mouseX, mouseY);
                if (style != null && this.handleTextClick(style)) {
                    return true;
                }
            }

            return this.chatField.mouseClicked(mouseX, mouseY, button) ? true : super.mouseClicked(mouseX, mouseY, button);
        }
    }

    protected void insertText(String text, boolean override) {
        if (override) {
            this.chatField.setText(text);
        } else {
            this.chatField.write(text);
        }

    }

    public void setChatFromHistory(int i) {
        int j = this.messageHistorySize + i;
        int k = this.client.inGameHud.getChatHud().getMessageHistory().size();
        j = MathHelper.clamp(j, 0, k);
        if (j != this.messageHistorySize) {
            if (j == k) {
                this.messageHistorySize = k;
                this.chatField.setText(this.field_2389);
            } else {
                if (this.messageHistorySize == k) {
                    this.field_2389 = this.chatField.getText();
                }

                this.chatField.setText((String)this.client.inGameHud.getChatHud().getMessageHistory().get(j));
                this.commandSuggestor.setWindowActive(false);
                this.messageHistorySize = j;
            }
        }
    }

    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.setFocused(this.chatField);
        this.chatField.setSelected(true);
        //ANTICRACK

        //fill(matrices, 2, this.height - 14, this.width - 2, this.height - 2, this.client.options.getTextBackgroundColor(rainbow(0*300)));

        fillGradient(matrices, 2, this.height - 14, this.width - 2, this.height - 2, rainbow(2 * 300), rainbow(0 * 300));
        fill(matrices, 3, this.height - 13, this.width - 3, this.height - 3, this.client.options.getTextBackgroundColor(-16777216)); //-2147483648 //-16777216
        this.chatField.render(matrices, mouseX, mouseY, delta);
        this.commandSuggestor.render(matrices, mouseX, mouseY);
        Style style = this.client.inGameHud.getChatHud().getText((double)mouseX, (double)mouseY);
        if (style != null && style.getHoverEvent() != null) {
            this.renderTextHoverEffect(matrices, style, mouseX, mouseY);
        }

        super.render(matrices, mouseX, mouseY, delta);
    }

    public boolean isPauseScreen() {
        return false;
    }

    private void setText(String text) {
        this.chatField.setText(text);
    }
    public static int rainbow(int delay) {
        double rainbowState = Math.ceil((System.currentTimeMillis() + delay) / 10.0);
        rainbowState %= 360;
        return Color.getHSBColor((float) (rainbowState / 360.0f), 1.0f, 1.0f).getRGB(); //0.8f, 0.7f
    }
}
