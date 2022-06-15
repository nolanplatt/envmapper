const quizData = [
    {
        question: "How long do you shower?",
        a: "10mins",
        b: "20mins",
        c: "30mins",
        d: "1hr plus",
        correct: "a",
    },
    {
        question: "Do you leave the sink on when you brush?",
        a: "yes",
        b: "no",
        correct: "b",
    },
    {
        question: "How often do you eat animal-based products?",
        a: "Once a week",
        b: "3 times a week",
        c: "every day",
        d: "Never",
        correct: "a",
    },
    {
        question: "Do You Have Energy Star Lightbulbs?",
        a: "No",
        b: "yes",
        correct: "b",
    },
    {
        question: "What Type of fuel do you use for Home Heating and Cooling?",
        a: "Fossil Fuel",
        b: "Electricity",
        c: "Green Electric",
        d: "None",
        correct: "a",
    },
    {
        question: "Is only cold water used for laundry?",
        a: "yes",
        b: "no",
        correct: "a",
    },
    {
        question: "What size square footage is your home?",
        a: "Small apartment (less than 900 sq feet)",
        b: "Average (approx. 2000 sqft)",
        c: "Large (over 4000 sqft)",
        d: "Other",
        correct: "a",
    },
    
    {
        question: "How do you get around?",
        a: "I walk or bike all the time",
        b: "I ride the bus",
        c: "A small  or midsize vehicle",
        d: "A large vehicle",
        correct: "b",
    },
    {
        question: "Are you, by any odd chance, vegan or vegeterian?",
        a: "yes",
        b: "no",
        correct: "b",
    },
    {
        question: "How often do you have dairy products?",
        a: "twice a week",
        b: "Every Day",
        correct: "Multiple",
    },


];
const quiz= document.getElementById('quiz')
const answerEls = document.querySelectorAll('.answer')
const questionEl = document.getElementById('question')
const a_text = document.getElementById('a_text')
const b_text = document.getElementById('b_text')
const c_text = document.getElementById('c_text')
const d_text = document.getElementById('d_text')
const submitBtn = document.getElementById('submit')


let currentQuiz = 0
let score = 0

loadQuiz()

function loadQuiz() {

    deselectAnswers()

    const currentQuizData = quizData[currentQuiz]

    questionEl.innerText = currentQuizData.question
    a_text.innerText = currentQuizData.a
    b_text.innerText = currentQuizData.b
    c_text.innerText = currentQuizData.c
    d_text.innerText = currentQuizData.d
}

function deselectAnswers() {
    answerEls.forEach(answerEl => answerEl.checked = false)
}

function getSelected() {
    let answer
    answerEls.forEach(answerEl => {
        if(answerEl.checked) {
            answer = answerEl.id
        }
    })
    return answer
}


submitBtn.addEventListener('click', () => {
    const answer = getSelected()
    if(answer) {
       if(answer === quizData[currentQuiz].correct) {
           score++
       }

       currentQuiz++

       if(currentQuiz < quizData.length) {
           loadQuiz()
       } else {
           quiz.innerHTML = `
           <h2>You answered ${score}/${quizData.length} questions correctly</h2>

           <button onclick="location.reload()">Reload</button>
           `
       }
    }
})