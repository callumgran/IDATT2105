import { createRouter, createWebHistory  } from 'vue-router';
import Calculator from '../views/Calculator.vue';
import ContactFormVue from '../components/ContactForm.vue';
import LoginForm from '../components/LoginForm.vue';
import RegisterForm from '../components/RegisterForm.vue';

const routes = [
    {
        path: '/calculator',
        name: 'Calculator',
        component: Calculator,
    },
    {
        path: '/contact',
        name: 'ContactForm',
        component: ContactFormVue,
    },
    {
        path: '/login',
        name: 'Login',
        component: LoginForm,
    },
    {
        path: '/register',
        name: 'Register',
        component: RegisterForm,
    },
];

const router = createRouter({
    history: createWebHistory(),
    routes
});

export default router;