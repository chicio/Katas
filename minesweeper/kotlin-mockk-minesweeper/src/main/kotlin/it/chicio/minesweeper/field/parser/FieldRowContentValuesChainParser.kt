package it.chicio.minesweeper.field.parser

class FieldRowContentValuesChainParser : FieldRowContentChainParser() {
    public override fun canParse(row: String?): Boolean {
        return isNotHeader(row) && isNotTermination(row)
    }

    @Throws(RuntimeException::class)
    public override fun parse(fieldsParsingStatus: FieldsParsingStatus): FieldsParsingStatus {
        val newFieldsParsingStatus = FieldsParsingStatus(fieldsParsingStatus)
        val columnsValues = newFieldsParsingStatus.currentRowContent!!.split(" ").toTypedArray()
        checkIfNumberOfValuesAreCorrect(newFieldsParsingStatus, columnsValues)
        setNewFieldRow(newFieldsParsingStatus, columnsValues)
        return newFieldsParsingStatus
    }

    private fun setNewFieldRow(newFieldsParsingStatus: FieldsParsingStatus, columnsValues: Array<String>) {
        for (column in 0 until newFieldsParsingStatus.currentField!!.numberOfColumn) {
            newFieldsParsingStatus.currentField!![newFieldsParsingStatus.currentRow, column] = columnsValues[column]
        }
        newFieldsParsingStatus.currentRow++
    }

    private fun checkIfNumberOfValuesAreCorrect(newFieldsParsingStatus: FieldsParsingStatus, columnsValues: Array<String>) {
        if (columnsValues.size != newFieldsParsingStatus.currentField!!.numberOfColumn) {
            throw RuntimeException()
        }
    }

    private fun isNotHeader(row: String?): Boolean {
        return !row!!.matches(Regex("(\\d \\d)"))
    }

    private fun isNotTermination(row: String?): Boolean {
        return row != "0 0"
    }
}