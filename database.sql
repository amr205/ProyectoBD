create database Hospital;
use Hospital;

/*--************************************** [Departamento]*/

create table Departamento( CveDepartamento varchar(4) not null,
			Nombre varchar(50),
			constraint DepartamentoPK primary key (CveDepartamento));



/*--************************************** [TipoMedicamento]*/

create table TipoMedicamento( CveTipoMedicamento varchar(4) not null,
			Descripcion varchar(200),
			constraint TipoMedicamento primary key (CveTipoMedicamento));


/*--************************************** [EspecialidadDoctor]*/

create table EspecialidadDoctor( CveEspecialidad varchar(4) not null,
			Descripcion varchar(200),
			constraint EspecialidadDoctorPK primary key (CveEspecialidad));


/*--************************************** [TIpoEnfermedad]*/

create table TipoEnfermedad( CveTipoEnfermedad varchar(4) not null,
			Descripcion varchar(200),
			constraint TipoEnfermedadPK primary key (CveTipoEnfermedad));



/*--************************************** [TipoPaciente]*/


create table TipoPaciente( CveTipoPaciente varchar(4) not null,
			Descripcion varchar(200),
			constraint TipoPacientePK primary key (CveTipoPaciente));


/*--************************************** [TipoEstudio]*/

create table TipoEstudio(CveTipoEstudio varchar(4) not null,
			Descripcion varchar(200),
			constraint TipoEstudioPK primary key (CveTipoEstudio));

/*--************************************** [SalaQuirofano]*/

create table SalaQuirofano( CveSala varchar(4) not null,
			Descripcion varchar(200),
			constraint SalaQuiforanoPK primary key (CveSala));



/*--************************************** [TipoHabitacion]*/


create table TipoHabitacion (CveTipoHabitacion varchar(4) not null,
			Descripcion varchar(200),
			constraint TipoHabitacionPK primary key (CveTipoHabitacion));

/*--************************************** [Enfermera]*/


create table Enfermera(	CveEnfermera varchar(4) not null,
			Nombre varchar(50),
			Edad tinyint,
			constraint EnfermeraPK primary key (CveEnfermera));

/*--************************************** [Doctor]*/

create table Doctor( Licencia varchar(20) not null,
			CveEspecialidad varchar(4),
			Nombre varchar(50),
			CURP varchar(20),
			Sexo varchar(1),
			Edad tinyint,
			constraint DoctorPK primary key (Licencia),
			constraint DoctorFK1 foreign key (CveEspecialidad) references EspecialidadDoctor(CveEspecialidad));



/*--************************************** [Medicamento]*/
create table Medicamento(CveMedicamento varchar(4) not null,
			CveTipoMedicamento varchar(4),
			Nombre varchar(50),
			constraint MedicamentoPK primary key (CveMedicamento),
			constraint MedicamentoFK1 foreign key (CveTipoMedicamento) references TipoMedicamento (CveTipoMedicamento));


/*--************************************** [Consultorio]*/


create table Consultorio( CveConsultorio varchar(4) not null,
			CveDepartamento varchar(4),
			constraint ConsultorioPK primary key (CveConsultorio),
			constraint ConsultorioFK foreign key (CveDepartamento) references Departamento(CveDepartamento));


/*--************************************** [Laboratorios]*/


create table Laboratorios( CveLaboratorios varchar(4) not null,
			CveDepartamento varchar(4),
			Descripcion varchar(200),
			constraint LaboratoriosPK primary key (CveLaboratorios),
			constraint LaboratoriosFK1 foreign key (CveDepartamento) references Departamento(CveDepartamento));


/*--************************************** [Paciente]*/


create table Paciente( CvePaciente varchar(4) not null,
			CveTipoPaciente varchar(4),
			Licencia varchar(20),
			Nombre varchar(50),
			Sexo varchar(1),
			Edad tinyint,
			CURP varchar(20),
			constraint PacientePK primary key (CvePaciente),
			constraint PacienteFK1 foreign key (CveTipoPaciente) references TipoPaciente(CveTipoPaciente),
			constraint PacienteFK2 foreign key (Licencia) references Doctor(Licencia));


/*--************************************** [Estudio]*/


create table Estudio( CveEstudio varchar(4) not null,
			Licencia varchar(20),
			CveTipoEstudio varchar(4),
			Razon varchar(200),
			Fechar date,
			constraint EstudioPK primary key (CveEstudio),
			constraint EstudioFK1 foreign key (Licencia) references Doctor(Licencia),
			constraint EstudioFK2 foreign key (CveTipoEstudio) references TipoEstudio(CveTipoEstudio));


/*--************************************** [Habitacion]*/


create table Habitacion(CveHabitacion varchar(4) not null,
			CveTipohabitacion varchar(4),
			CvePaciente varchar(4),
			Descipcion varchar(200),
			constraint HabitacionPK primary key (CveHabitacion),
			constraint HabitacionFK1 foreign key (CveTipoHabitacion) references TipoHabitacion(CveTipoHabitacion),
			constraint HabitacionFK2 foreign key (CvePaciente) references Paciente(CvePaciente));

/*--************************************** [Asignado]*/

create table Asignado(	CvePaciente varchar(4) not null,
			CveEnfermera varchar(4) not null,
			constraint AsignadoPK primary key (CvePaciente,CveEnfermera),
			constraint AsignadoFK1 foreign key (CvePaciente) references Paciente(CvePaciente),
			constraint AsignadoFK2 foreign key (CveEnfermera) references Enfermera(CveEnfermera));



/*--************************************** [Operacion]*/

create table Operacion( CvePaciente varchar(4) not null,
			Numero int not null,
			CveSala varchar(4),
			Descripcion varchar(200),
			Fecha date,
			constraint OperacionPK primary key (CvePaciente, Numero),
			constraint OperacionFK1 foreign key (CvePaciente) references Paciente(CvePaciente),
			constraint OperacionFK2 foreign key (CveSala) references SalaQuirofano(CveSala));


/*--************************************** [Consulta]*/

create table Consulta(	CveConsulta varchar(4) not null,
			CveConsultorio varchar(4),
			CvePaciente varchar(4),
			constraint ConsultaPK primary key (CveConsulta),
			constraint ConsultaFK1 foreign key (CveConsultorio) references Consultorio(CveConsultorio));


/*--************************************** [Receta]*/
create table Receta( CveMedicamento varchar(4) not null,
			CveConsulta varchar(4) not null,
			constraint RecetaPK primary key (CveMedicamento,CveConsulta),
			constraint RecetaFK1 foreign key (CveMedicamento) references Medicamento(CveMedicamento),
			constraint RecetaFK2 foreign key (CveConsulta) references Consulta(CveConsulta));



/*--************************************** [Diagnostico]*/

create table Diagnostico( CveConsulta varchar(4) not null,
			Numero int not null,
			CveTipoEnfermedad varchar(4),
			Enfermedad varchar(50),
			Descripcion varchar(200),
			constraint DiagnosticoPK primary key (CveConsulta, Numero),
			constraint DiagnosticoFK foreign key (CveTipoEnfermedad) references TipoEnfermedad(CveTipoEnfermedad));