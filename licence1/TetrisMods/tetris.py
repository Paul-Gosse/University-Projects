import tetris1v1,tetrissolo

import random, time, pygame, sys
from pygame.locals import *

pygame.init()
pygame.display.set_caption('Tetris')
DISPLAYSURF = pygame.display.set_mode((640, 480))
ready=False
while not(ready):
    DISPLAYSURF.blit(pygame.image.load("image/menu.jpg"),(0,0))
    pygame.display.update()
    for event in pygame.event.get():
        if event.type == KEYUP:
            if event.key==K_F1:
                tetrissolo.main()
                ready=True
            elif event.key==K_F2:
                tetris1v1.main()
                ready=True
    
