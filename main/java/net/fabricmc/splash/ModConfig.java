package net.fabricmc.splash;

//Copyright to Splash Client 2017-2020(©)
//This client is free to use, however copying/modifying its code is strictly forbidden.
//If you have any questions about fair use, contact Splash#7713 on Discord.

import net.fabricmc.splash.utils.FileManager;

public class ModConfig {



        public static boolean enableGlint = false;
        public static boolean enableTime = false;
        public static boolean enablePlayRen = false;
        public static boolean enableRainbow = false;
        public static boolean enableNoFire = false;
        public static boolean enableNoHurt = false;
        public static boolean enableArmourHUD = false;
        public static boolean enableHealthInd = false;
        public static boolean enableTotemC = false;
        public static boolean enableHitbox = false;
        public static boolean noifications = true;
        public static boolean armorposition = false;
        public static boolean transparent = true;
        public static boolean rgb = true;

        public static float red;

        public static int armour = 0;

        //## Don't Touch, will mess shit up ##
        public static boolean notify = false;

        public static float getRed() {
                return between(red, 0.0F, 1.0F);
        }

        public static void setRed(float r) {
                red = between(r, 0.0F, 1.0F);
        }
        private static float between(float i, float x, float y) {
                if (i < x)
                        i = x;
                if (i > y)
                        i = y;
                return i;
        }

        private static int between(int i, int x, int y) {
                if (i < x)
                        i = x;
                if (i > y)
                        i = y;
                return i;
        }
}

