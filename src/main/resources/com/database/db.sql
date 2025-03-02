
CREATE TABLE IF NOT EXISTS myTable (
    Date VARCHAR(255),
    Name VARCHAR(255),
    MRN VARCHAR(255),
    Procedure_Notes VARCHAR(10000),
    code_99203 INT,
    code_99204 INT,
    code_99205 INT,
    code_99213 INT,
    code_99214 INT,
    code_99215 INT,
    code_99221 INT,
    code_99222 INT,
    code_99223 INT,
    code_99231 INT,
    code_99232 INT,
    code_99233 INT
);

-- SELECT * FROM CSVREAD('file:/Users/jacksonkotch/Desktop/Database/src/main/resources/com/database/EP Coding Feb 2025.csv', null, 'header = true');