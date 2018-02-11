package com.TheRedSpy15.trail;

/*

   Copyright 2018 TheRedSpy15

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

 */

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class GameOverController extends Main {

    private String[] ranks = {"Dummy","Meh","Introvert","Loner","CHEATER!!!"};

    @FXML
    private Label scoreLbl, rankLbl;

    @FXML private void initialize(){

        scoreLbl.setText("Score: "+ Main.gang.getScore());

        rankLbl.setText("Rank: "+determineRank());
    }

    private String determineRank(){

        int rank4 = 500_000;
        int rank3 = 100_000;
        int rank2 = 50_000;
        int rank1 = 25_000;

        if (Main.gang.getScore() >= rank4) return ranks[4];
        else if (Main.gang.getScore() >= rank3) return ranks[3];
        else if (Main.gang.getScore() >= rank2) return ranks[2];
        else if (Main.gang.getScore() >= rank1) return ranks[1];
        else return ranks[0];
    }
}
