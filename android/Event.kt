package com.my.package

/**
 * Вспомогательный класс для формирования event-like поведения у LiveData. LiveData используется для обработки
 * жизненного цикла observer-a, обычная лямбда для сравнения может сработать "в молоко", если приложение в фоне
 *
 * @property content Generic контент
 * @property hasBeenHandled Булевый признак того, что событие уже обработано, по умолчанию false, выставится в true после
 * получения контента
 */
open class Event<out T>(private val content: T) {

    var hasBeenHandled = false
        private set

    /**
     * Получить контент из события. Если событие еще не было обработано (см [hasBeenHandled]) вернет контент, иначе null
     */
    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

    /**
     * Метод на случай, если событие уже было обработано, но тем не менее необходимо получить контент
     */
    fun peekContent(): T = content
}

// использование: val settingsChangedEvent: MutableLiveData<Event<Unit>> = MutableLiveData()

