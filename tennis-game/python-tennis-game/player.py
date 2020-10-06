from enum import Enum
from typing import Callable

from score import Score


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


def read_player(write_to_console: Callable[[str], None], read_from_console: Callable[[], str], parse_input_player: Callable[[str], InputPlayer]) -> InputPlayer:
    write_to_console("Which player will play (1 or 2)?")
    return parse_input_player(read_from_console())


def parse_input_player(input: str) -> InputPlayer:
    if input == "1":
        return InputPlayer.Player1

    if input == "2":
        return InputPlayer.Player2

    return InputPlayer.UnknownPlayer
