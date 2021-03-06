/****** Script for SelectTopNRows command from SSMS  ******/
SELECT sasa.StudentUSI
      ,sasa.AssessmentTitle
      ,sasa.AcademicSubjectTypeId
      ,sasa.AssessedGradeLevelTypeId
      ,sasa.Version
      ,sasa.AdministrationDate
      ,sat.CodeValue as SpecialAccommodationsType
  FROM EdFi.edfi.StudentAssessmentSpecialAccommodations sasa
  LEFT JOIN EdFi.edfi.SpecialAccommodationsType sat ON sasa.SpecialAccommodationsTypeId = sat.SpecialAccommodationsTypeId
  ORDER BY sasa.StudentUSI, sasa.AssessmentTitle, sasa.AcademicSubjectTypeId, sasa.AssessedGradeLevelTypeId, sasa.Version, sasa.AdministrationDate
