SELECT
MoxyFunctionName,
CASE
	WHEN MoxyFunctionGroupID IN (2) THEN 'Create and Approve'
	WHEN MoxyFunctionGroupID IN (3,4) THEN 'Trade'
	WHEN MoxyFunctionGroupID IN (5,6,7) THEN 'Modify'
	WHEN MoxyFunctionGroupID IN (8,9,10) THEN 'Allocate'
	WHEN MoxyFunctionGroupID IN (11,12) THEN 'Portfolio Access'
	WHEN MoxyFunctionGroupID IN (14) THEN 'New Orders'
	WHEN MoxyFunctionGroupID IN (15) THEN 'Order Out of Balance'
	WHEN MoxyFunctionGroupID IN (16) THEN 'Broker Conflicts'
	WHEN MoxyFunctionGroupID IN (17) THEN 'Miscellaneous'
	WHEN MoxyFunctionGroupID IN (18,22,23,24,25,26,27,28) THEN 'Restriction Checking'
	WHEN MoxyFunctionGroupID IN (30,31,32,33) THEN 'Ticket Reports'
	WHEN MoxyFunctionGroupID IN (34) THEN 'Order Reports'
	WHEN MoxyFunctionGroupID IN (36,37) THEN 'Internal Reports'
	WHEN MoxyFunctionGroupID IN (38) THEN 'Portfolio Reports'
	WHEN MoxyFunctionGroupID IN (39) THEN 'Audit Reports'
	WHEN MoxyFunctionGroupID IN (41,42,43,44,45,46) THEN 'System Reports'
	WHEN MoxyFunctionGroupID IN (47) THEN 'Administration'
	WHEN MoxyFunctionGroupID IN (48,49,50,51) THEN 'System Tables'
	ELSE '???'
	END AS 'FunctionGroupName',
FunctionDescription,
FunctionalAccessGroupName,
AccessRight
FROM MoxyFunctionalAccessRights FAR
JOIN MoxyFunctionalAccessGroups AG ON FAR.FunctionalAccessGrpID = AG.FunctionalAccessGrpID
JOIN MoxyFunctions F ON FAR.MoxyFunctionID = F.MoxyFunctionID
ORDER BY AG.FunctionalAccessGrpID, F.MoxyFunctionGroupID