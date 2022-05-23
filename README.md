# Prog

Trois classes : batailleNavalle
plateau
case
bateau
joueur

## bataillenavale:
comporte:  
deroulerpartie()  
jouer() 
init()

implement actionlistener

## plateau
c le plateau
on commence à 16, nombre de bateau = (nb de case/8)²  

## enum case
définit case en tant que 
occupé  
vide  
coulé

je le garde la au cas ou
System.out.println("bot memory " + ib.botMemory + " " + ib.botHitCpt);
                if(ib.botHitCpt<2){
                    if (new Random().nextInt(2)==0){
                        System.out.println("on essaye");

                        if (new Random().nextInt(2) == 0 & ib.lastBotHit[0] != gameSize){
                            ib.remplir(ib.lastBotHit[0] + 1, ib.lastBotHit[1], 1);
                        }
                        else if (ib.lastBotHit[1] != gameSize) {
                        ib.remplir(ib.lastBotHit[0], ib.lastBotHit[1] + 1, 1);
                        }
                        else{
                            ib.remplir(ib.lastBotHit[0], ib.lastBotHit[1] - 1, 1);
                        }
                    }
                    else{
                        if (new Random().nextInt(1)==0 & ib.lastBotHit[0] != 0){
                            ib.remplir(ib.lastBotHit[0] - 1, ib.lastBotHit[1], 1);
                        }
                        else if (ib.lastBotHit[1] != 0) {
                        ib.remplir(ib.lastBotHit[0], ib.lastBotHit[1] - 1, 1);
                        }
                        else{
                            ib.remplir(ib.lastBotHit[0], ib.lastBotHit[1] + 1, 1);
                        }
                    }
                }
                else{
                    if (ib.botMemory == 2){
                        botWay = botWay * -1;
                        int botWaytemp = botWay;

                        
                        if (ib.botHit[0] - ib.lastBotHit[0] != 0){

                            while(!checkIfBotPlayable(ib.botHit[0] + botWaytemp, ib.botHit[1]) & ib.botHit[0] + botWaytemp != gameSize){
                                if(botWaytemp < 0){
                                    botWaytemp ++;
                                }
                                else{
                                    botWaytemp --;
                                }
                                System.out.println("while 1");
                            }
                            System.out.println(ib.botHit[0] + botWaytemp);
                            if(ib.botHit[0] + botWaytemp != gameSize - 1 & checkIfBotPlayable(ib.botHit[0] + botWay, ib.botHit[1])){
                                ib.remplir(ib.botHit[0] + botWaytemp, ib.botHit[1], 1);
                            }
                            else{
                                ib.botMemory = 2;
                            }
                            

                        }
                        else{

                            while(!checkIfBotPlayable(ib.botHit[0] , ib.botHit[1] + botWaytemp) & ib.botHit[1] + botWaytemp != gameSize){
                                if(botWaytemp < 0){
                                    botWaytemp ++;
                                }
                                else{
                                    botWaytemp --;
                                }
                                System.out.println("while 2");
                            }
                            System.out.println(ib.botHit[1] + botWaytemp);

                            if(ib.lastBotHit[0] != gameSize - 1 & checkIfBotPlayable(ib.lastBotHit[0] + botWay, ib.lastBotHit[1])){
                                ib.remplir(ib.botHit[0], ib.botHit[1] + botWaytemp, 1);
                            }
                            else{
                                ib.botMemory = 2;
                            }
                        }
                        
                    }       
                    else if(ib.botHitCpt == 2){   
                        if (ib.botHit[0] - ib.lastBotHit[0] != 0){
                            if (ib.botHit[0] - ib.lastBotHit[0] < 0 ){
                                botWay = 1;
                                if(ib.lastBotHit[0] != gameSize - 1 & checkIfBotPlayable(ib.lastBotHit[0] + botWay, ib.lastBotHit[1])){
                                    ib.remplir(ib.lastBotHit[0] + botWay, ib.lastBotHit[1], 1);
                                }
                            }
                            else{
                                botWay = -1;
                                if(ib.lastBotHit[1] != gameSize - 1 & checkIfBotPlayable(ib.lastBotHit[0] + botWay, ib.lastBotHit[1])){
                                    ib.remplir(ib.lastBotHit[0] + botWay, ib.lastBotHit[1], 1);
                                }
                            }
                        }   
                        else{
                            if (ib.botHit[1] - ib.lastBotHit[1] < 0 ){
                                botWay = 1;
                                if(ib.lastBotHit[0] != gameSize - 1 & checkIfBotPlayable(ib.lastBotHit[0], ib.lastBotHit[1] + botWay)){
                                    ib.remplir(ib.lastBotHit[0], ib.lastBotHit[1] + botWay, 1);
                                }
                            }
                            else{
                                botWay = -1;
                                if(ib.lastBotHit[1] != gameSize - 1 & checkIfBotPlayable(ib.lastBotHit[0], ib.lastBotHit[1] + botWay)){
                                    ib.remplir(ib.lastBotHit[0], ib.lastBotHit[1] + botWay, 1);
                                }
                            }
                        }
  
                    }       
                    else{
                        System.out.println("dernière option");
                        if (ib.botHit[0] - ib.lastBotHit[0] != 0){
                            if(ib.lastBotHit[0] != gameSize - 1 & checkIfBotPlayable(ib.lastBotHit[0] + botWay, ib.lastBotHit[1])){
                                ib.remplir(ib.lastBotHit[0] + botWay, ib.lastBotHit[1], 1);
                            }
                            else{
                                ib.botMemory = 2;
                            }
                        }
                        else{
                            if(ib.lastBotHit[0] != gameSize - 1 & checkIfBotPlayable(ib.lastBotHit[0] + botWay, ib.lastBotHit[1])){
                                ib.remplir(ib.lastBotHit[0], ib.lastBotHit[1] + botWay, 1);
                            }
                            else{
                                ib.botMemory = 2;
                            }
                        }

                    }


                }


