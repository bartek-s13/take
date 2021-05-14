using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

// Code scaffolded by EF Core assumes nullable reference types (NRTs) are not used or disabled.
// If you have enabled NRTs for your project, then un-comment the following line:
// #nullable disable

namespace CompanyApi.Models
{
    public partial class Department
    {
        public Department()
        {
            Employee = new HashSet<Employee>();
        }

        [Key]
        public int DepartmentId { get; set; }
        [StringLength(20)]
        public string Name { get; set; }

        [InverseProperty("Department")]
        public virtual ICollection<Employee> Employee { get; set; }
    }
}
