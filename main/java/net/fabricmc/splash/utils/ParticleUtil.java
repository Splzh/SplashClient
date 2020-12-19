package net.fabricmc.splash.utils;

import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//Copyright to Splash Client 2017-2020(©)
//This client is free to use, however copying/modifying its code is strictly forbidden.
//If you have any questions about fair use, contact Splash#7713 on Discord.

public class ParticleUtil extends DrawableHelper {

    private final List<Particle> particles;
    private int width, height, count;

    public ParticleUtil(final int width, final int height) {
        this.width = width;
        this.height = height;
        this.count = 1;
        this.particles = new ArrayList<Particle>();
        for (int count = 0; count <= this.count; ++count) {
            this.particles.add(new Particle(new Random().nextInt(width), new Random().nextInt(height)));
        }
    }

    public void drawParticles(MatrixStack matrices) {
        this.particles.forEach(particle -> particle.drawParticle(matrices));
    }


    public class Particle {

        private int xPos, yPos;

        public Particle(final int xPos, final int yPos) {
            ++this.xPos;
            ++this.yPos;
            this.xPos = xPos;
            this.yPos = yPos;
        }

        public void drawParticle(MatrixStack matrices) {
            this.xPos += new Random().nextInt(2);
            this.yPos += new Random().nextInt(2);
            final int particleSize = 2;

            Random ran = new Random();
            int x = ran.nextInt(2) + 1;

            if (this.xPos > ParticleUtil.this.width) { //>
                this.xPos = -particleSize;
            }

            if (this.yPos > ParticleUtil.this.height) { //>
                this.yPos = -particleSize;
            }
                fill(matrices, this.xPos, this.yPos, this.xPos + particleSize, this.yPos + particleSize, rainbow(0*300)); //822083583 //Color.WHITE.getRGB()

        }


    }

    public static int rainbow(int delay) {
        double rainbowState = Math.ceil((System.currentTimeMillis() + delay) / 25.0);

        rainbowState %= 360;
        return Color.getHSBColor((float) (rainbowState / 360.0f), 0.8f, 0.7f).getRGB();
    }

}

