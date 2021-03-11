package stats;

import com.zeroz.games.Main;

public class EnemyStats {

        private Main mainGame;
        private int enemyLevel = 3;
        private int enemyHP = 100;
        private int enemyAP = 3;

        public EnemyStats(Main mainGame) {
            this.mainGame = mainGame;
        }

        public int getEnemyLevel() {
                return enemyLevel;
        }

        public void setEnemyLevel(int enemyLevel) {
                this.enemyLevel = enemyLevel;
        }

        public int getEnemyHP() {
                return enemyHP;
        }

        public void setEnemyHP(int enemyHP) {
                this.enemyHP = enemyHP;
        }

        public int getEnemyAP() {
                return enemyAP;
        }

        public void setEnemyAP(int enemyAP) {
                this.enemyAP = enemyAP;
        }
}
