module.exports = {
    createInterface: jest.fn().mockReturnValue({
        question: jest.fn()
            .mockImplementationOnce((_questionTest, cb) => cb("1"))
            .mockImplementationOnce((_questionTest, cb) => cb("1"))
            .mockImplementationOnce((_questionTest, cb) => cb("1"))
            .mockImplementationOnce((_questionTest, cb) => cb("1"))
            .mockImplementationOnce((_questionTest, cb) => cb("2"))
            .mockImplementationOnce((_questionTest, cb) => cb("2"))
            .mockImplementationOnce((_questionTest, cb) => cb("2"))
            .mockImplementationOnce((_questionTest, cb) => cb("99"))
            .mockImplementationOnce((_questionTest, cb) => cb("2")),
        close: jest.fn().mockImplementationOnce(() => undefined)
    })
};
