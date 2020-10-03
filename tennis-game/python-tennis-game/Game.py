from Player import Player
from Score import Score


class Game:
    def __init__(self, player1: Player, player2: Player):
        self.player1 = player1
        self.player2 = player2

    def completed(self) -> bool:
        return self.player1.score == Score.Wins or self.player1.score == Score.Wins

    def __eq__(self, other):
        if not isinstance(other, Game):
            return False

        return self.player1 == other.player1 and self.player2 == other.player2
