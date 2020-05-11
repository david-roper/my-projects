'''
David Roper
420-LCU, computer programming, section 1
May 6th
Epreuve synthese
'''
                                    #Blackjack
start = True
while start == True: #while loop to allow game to be played more than once
       print('''Welcome to Blackjack the computer card game!
                Type 1 to play
                Type anything else to quit''')
       op1 = input('option:') #ask for option
       
       if op1 == '1':
              start == False #stops option menu from popping up

              #set up the lists for the suits, determined by first letter and their #
              hearts = ['h1','h2','h3','h4','h5','h6','h7','h8','h9','h10','hJ','hQ','hK']
              diamonds = ['d1','d2','d3','d4','d5','d6','d7','d8','d9','d10','dJ','dQ','dK']
              spades = ['s1','s2','s3','s4','s5','s6','s7','s8','s9','s10','sJ','sQ','sK']
              clubs = ['c1','c2','c3','c4','c5','c6','c7','c8','c9','c10','cJ','cQ','cK']

              from random import shuffle #shuffle the deck
              d = hearts + diamonds + spades + clubs
              shuffle(d) 

              #set variables for point total and hands for player/dealer
              phand = [] #list of player cards
              dhand = []#list of dealer cards
              ptot = 0 #sum of player cards
              dtot = 0 #sum of dealer cards


              def val(d): #function to add cards numerical value to player/dealer total
                     '''This function add the value of the cards to the players or dealer total
                     converts cards numeric value to int, changes face cards to a value of 10'''
                     if d[0][1:].isdigit():
                            if d[0][1:] == '1':
                                   return 0
                            else:
                                   val = int(d[0][1:])
                            
                            return val
                     else:
                            return 10
              def ace(x):
                     '''This function checks for an ace in the hand and determines
                     and if there is one, determines whats value the ace should be'''
                     htot = 0 #checks total of hand 
                     ac = 0 #number of aces
                     for val2 in x: #sum for hand
                            if val2[1:].isdigit():
                                  htot += int(val2[1:])
                            else:
                                  htot += 10
                     for ace in x:
                                   if ace == 'h1' or ace == 'c1'or ace == 's1'or ace == 'd1':
                                          ac += 1
                                          
                     if htot < 11:   #if total sum of cards under 11  
                            if ac > 1: #if more than 2 aces
                                   return 1
                            elif ac == 1: #1 ace
                                   return 11
                            else:
                                   return 0 #no ace
                     elif htot > 11 and ac >= 1: #if sum of hand is over eleven ace = 1 
                            return 1
                     else:
                            return 0 #return zero if no ace found
              def hand_check(ls, tot):
                     '''This function is the final check of the hand before determining a winner, if the hand is
                     over 21 but there is an ace, the ace is converted to have a value of 1'''
                    
                     ac = 0
                     for ace in ls: #scans hand for ace(s)
                                   if ace == 'h1' or ace == 'c1'or ace == 's1'or ace == 'd1':
                                          ac += 1
                     if tot >= 21 and ac == 0: #if no ace found leave as is
                           return tot
                     elif tot > 21 and ac == 2: #if 2 aces and total is over 21 convert to 1
                           return (tot - 10)
                     elif tot > 21 and ac == 1: #if 1 ace is found and tot is over 21 convert from 11 to 1
                           return (tot - 10)
                     elif tot <= 21 and ac == 1: #if less than 21 just keep as is
                           return tot
                     else:
                           return tot
                     
                     
                     
              while True:
                     print("Hit me! (1), hold (0)")
                     op2 = input('enter option here:')
                     if op2 == '1': #draws first card from shuffled deck then 2nd for dealer
                            
                            if d[0][1:] != '1': #if next card is not an ace use val function
                                   ptot += val(d)
                                   a = d.pop(0) #pops card from list and adds it to hand
                                   phand.append(a)
                            else: #if it is an ace use ace function to add to total
                                   a = d.pop(0) 
                                   phand.append(a)
                                   ptot += ace(phand)
                                   

                            if dtot < 17: #if dealer is already at 17 he holds
                                   if d[0][1:] != '1': 
                                          dtot += val(d)
                                          b = d.pop(0) #does the same but for dealer
                                          dhand.append(b)
                                   else:
                                          b = d.pop(0)
                                          dhand.append(b)
                                          dtot += ace(dhand)
                            else:
                                  b = "dealer holds!"
                                  
                            if len(a) <= 3 and len(b) <= 3: #if dealer doesn't hold
                                   print('you drew a {}, dealer drew a {}'.format(a,b))
                            else: 
                                   print('you drew a {}, {}'.format(a,b)) #if dealer holds
                                   
                            print('your hand {}'.format(phand)) #shows player their hand
                            
                            #check hands to see if there's a winner after the deal or someone went over 21
                            #final check
                            dtot = hand_check(dhand, dtot)
                            ptot = hand_check(phand, ptot)

                            if ptot > 21 and dtot > 21:
                                   print('you both lose! (both over 21)')
                                   break
                            elif ptot > 21:
                                   print('you lose! (over 21)')
                                   break
                            elif dtot > 21:
                                   print('you win! (dealer over 21)')
                                   break
                            elif dtot == 21:
                                   print('you lose! (dealer at 21)')
                                   break
                            elif ptot == 21:
                                   print('you win!(at 21)')
                                   break
                            else:
                                 continue  
                            
                     else: #option to hold is taken dealer can still go
                            while dtot < ptot:
                                   #dealer still goes until he's closer to 21
                                   if d[0][1:] != '1':
                                          dtot += val(d)
                                          b = d.pop(0)
                                          dhand.append(b)
                                   else:
                                          b = d.pop(0)
                                          dhand.append(b)
                                          dtot += ace(dhand)
                                   print('you hold!, dealer drew a {}'.format(b))
                            #if player holds the hands are checked to determine winner
                                   #Whoever is closest to but not above 21 is the winner
                            #final check
                            dtot = hand_check(dhand, dtot)
                            ptot = hand_check(phand, ptot)
                            
                            if dtot <= 0 and ptot <= 0:
                                   print('no cards dealt')
                                   break
                            elif dtot > 21:
                                   print('you win!(dealer over 21)')
                                   break
                            elif dtot > ptot:
                                   print('you lose!(dealer closer to 21)')
                                   break
                            elif dtot < ptot:
                                   print('you win!(closer to 21)')
                                   break
                            else:
                                   print('tie')
                                   break
       else:
              print('Bye!') #leaves program
              break
