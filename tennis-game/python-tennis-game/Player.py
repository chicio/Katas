from enum import Enum

from Score import Score


class Player:
    def __init__(self, score: Score):
        self.score = score

    def __eq__(self, other) -> bool:
        if not isinstance(other, Player):
            return False

        return self.score == other.score


class InputPlayer(Enum):
    Player1 = 1
    Player2 = 2
    UnknownPlayer = 3


def read_player(write_to_console, read_from_console, parse_input_player):
    pass


def parse_input_player(input):
    pass

