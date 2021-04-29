import Vue from 'vue'
import VueRouter from 'vue-router'
import DoctorsList from 'components/DoctorsList.vue'
import DoctorRow from 'components/DoctorRow.vue'
import DoctorForm from 'components/DoctorForm.vue'
import PatientsList from 'components/PatientsList.vue'
import PatientForm from 'components/PatientForm.vue'
import Patients from 'components/Patients.vue'
import Doctors from 'components/Doctors.vue'
import About from 'components/About.vue'


Vue.use(VueRouter)

const routes = [
  { path: '/', component: Doctors},
  {path: '/patients/form/:id',name: 'createPatient', component: PatientForm},
  {path: '/doctors/form', name: 'createDoctor',component: DoctorForm},
  { path: '/doctors/:id', name: 'patient', component: Patients},
  { path: '/about', component: About},
  { path: '*', component: Doctors}
]

export default new VueRouter({
mode: 'history',
  routes
})