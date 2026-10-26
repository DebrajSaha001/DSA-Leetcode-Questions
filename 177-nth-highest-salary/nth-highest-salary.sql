CREATE FUNCTION getNthHighestSalary(N INT) RETURNS INT
BEGIN
    SET N = N - 1;
  RETURN (
    # Write your MySQL query statement below.
      Select Distinct salary
      from Employee
      Order by salary desc
      Limit 1 offset N
  );
END
