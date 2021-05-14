using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

// Code scaffolded by EF Core assumes nullable reference types (NRTs) are not used or disabled.
// If you have enabled NRTs for your project, then un-comment the following line:
// #nullable disable

namespace CompanyApi.Models
{
    public partial class Employee
    {
        public Employee()
        {
            Subordinate = new HashSet<Employee>();
        }

        [Key]
        public int EmployeeId { get; set; }
        [StringLength(15)]
        public string FirstName { get; set; }
        [StringLength(15)]
        public string LastName { get; set; }
        public int? ManagerId { get; set; }
        [Column(TypeName = "decimal(6, 2)")]
        public decimal? Salary { get; set; }
        [Column(TypeName = "decimal(6, 2)")]
        public decimal? Bonus { get; set; }
        public int? DepartmentId { get; set; }

        [ForeignKey(nameof(DepartmentId))]
        [InverseProperty("Employee")]
        public virtual Department Department { get; set; }
        [ForeignKey(nameof(ManagerId))]
        [InverseProperty(nameof(Employee.Subordinate))]
        public virtual Employee Manager { get; set; }
        [InverseProperty(nameof(Employee.Manager))]
        public virtual ICollection<Employee> Subordinate { get; set; }
    }
}
