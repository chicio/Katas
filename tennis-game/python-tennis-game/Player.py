from Score import Score


class Player:
    def __init__(self, score: Score):
        self.score = score

    def __eq__(self, other) -> bool:
        if not isinstance(other, Player):
            return False

        return self.score == other.score


def read_player():
    pass


def parse_input_player(input):
    pass

