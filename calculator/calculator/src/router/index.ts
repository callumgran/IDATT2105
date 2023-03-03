import { createRouter, createWebHistory  } from 'vue-router';
import Calculator from '../views/Calculator.vue';
import ContactFormVue from '../components/ContactForm.vue';

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
];

const router = createRouter({
    history: createWebHistory(),
    routes
});

export default router;