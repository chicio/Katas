from Game import GameFactory


def tennis_game(
        write_to_console,
        read_player,
        calculate_game_score,
        print_game) -> None:
    write_to_console("Welcome to the Tennis Game!")
    game = GameFactory.make()
    while not game.completed():
        game = calculate_game_score(game, read_player())
        print_game(game)
